package crudpapeleria.dao;

import crudpapeleria.core.Database;
import crudpapeleria.model.Category;
import crudpapeleria.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao {
    private final Database db = Database.getInstance();

    // Método para obtener todos los productos, ahora incluye la categoría
    public List<Product> getAllProducts() {
        ResultSet result = db.query("SELECT p.id_producto, p.nombre_producto, p.descripcion_producto, p.precio_producto, p.cantidad_producto, p.id_categoria, c.nombre_categoria FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria");
        List<Product> products = new ArrayList<>();

        try {
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id_producto"));
                product.setName(result.getString("nombre_producto"));
                product.setDescription(result.getString("descripcion_producto"));
                product.setPrice(result.getDouble("precio_producto"));
                product.setQuantity(result.getInt("cantidad_producto"));

                // Asignamos la categoría
                Category category = new Category(result.getInt("id_categoria"), result.getString("nombre_categoria"));
                product.setCategory(category);

                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return products;
    }

    // Método para obtener un solo producto, con su categoría
    public Product getProduct(int productId) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("SELECT p.id_producto, p.nombre_producto, p.descripcion_producto, p.precio_producto, p.cantidad_producto, p.id_categoria, c.nombre_categoria FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria WHERE p.id_producto = ?");
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id_producto"));
                product.setName(result.getString("nombre_producto"));
                product.setDescription(result.getString("descripcion_producto"));
                product.setPrice(result.getDouble("precio_producto"));
                product.setQuantity(result.getInt("cantidad_producto"));

                // Asignamos la categoría
                Category category = new Category(result.getInt("id_categoria"), result.getString("nombre_categoria"));
                product.setCategory(category);

                return product;
            }

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    // Método para crear un producto con su categoría
public int createProduct(Product product) {
    try {
        PreparedStatement statement = db.getConnection()
            .prepareStatement("INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, cantidad_producto, id_categoria) VALUES (?, ?, ?, ?, ?)", 
            PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setInt(5, product.getCategory().getId());

        if (statement.executeUpdate() > 0) {
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                return result.getInt(1);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    }
    return 0;
}

// Método para eliminar un producto
public boolean deleteProduct(int productId) {
    try {
        PreparedStatement statement = db.getConnection()
            .prepareStatement("DELETE FROM productos WHERE id_producto = ?");
        statement.setInt(1, productId);
        return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
        return false;
    }
}

// Método para actualizar un producto y su categoría
public boolean updateProduct(int productId, Product product) {
    try {
        PreparedStatement statement = db.getConnection()
            .prepareStatement("UPDATE productos SET nombre_producto = ?, descripcion_producto = ?, precio_producto = ?, cantidad_producto = ?, id_categoria = ? WHERE id_producto = ?");
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getQuantity());
        statement.setInt(5, product.getCategory().getId());
        statement.setInt(6, productId);
        
        return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
        return false;
    }
}

}