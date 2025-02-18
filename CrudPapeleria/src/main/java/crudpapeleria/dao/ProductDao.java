package crudpapeleria.dao;

import crudpapeleria.core.Database;
import crudpapeleria.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    private final Database db = Database.getInstance();
    
    public List<Product> getAllProducts() {
        ResultSet result = db.query("SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_producto FROM productos");
        List<Product> products = new ArrayList<>();
        
        try {
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id_producto"));
                product.setName(result.getString("nombre_producto"));
                product.setDescription(result.getString("descripcion_producto"));
                product.setPrice(result.getDouble("precio_producto"));
                product.setQuantity(result.getInt("cantidad_producto"));

                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return products;
    }
    
    public Product getProduct(int productId) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, cantidad_producto FROM productos WHERE id_producto = ?");
        
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id_producto"));
                product.setName(result.getString("nombre_producto"));
                product.setDescription(result.getString("descripcion_producto"));
                product.setPrice(result.getDouble("precio_producto"));
                product.setQuantity(result.getInt("cantidad_producto"));
                
                return product;
            }
            
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public int createProduct(Product product) {
        try {
            PreparedStatement statement = db.getConnection()
                .prepareStatement("INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, cantidad_producto) VALUES (?, ?, ?, ?)");
            
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            
            if (!statement.execute()) {
                return 0;
            }
            
            ResultSet result = statement.getGeneratedKeys();
            
            if (result.next()) {
                return result.getInt(1);
            }
            
            return 0;
        } catch (Exception ex) {
            return 0;
        }
    }
    
    public boolean deleteProduct(int productId) {
        try {
            PreparedStatement statement = db.getConnection()
                .prepareStatement("DELETE FROM productos WHERE id_producto = ?");
            
            statement.setInt(1, productId);
            return statement.execute();
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean updateProduct(int productId, Product product) {
        try {
            PreparedStatement statement = db.getConnection()
                .prepareStatement("UPDATE productos SET nombre_producto = ?, descripcion_producto = ?, precio_producto = ?, cantidad_producto = ? WHERE id_producto = ?");
            
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, productId);
            return statement.execute();
        } catch (Exception ex) {
            return false;
        }
    }
}