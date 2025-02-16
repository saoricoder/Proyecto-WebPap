/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import conexion.Database;
import model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */

public class ProductoDAO {
    
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        // Se utiliza Database.getInstance().getConnection() para obtener la conexión
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()){
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setDescripcion_producto(rs.getString("descripcion_producto"));
                producto.setPrecio_producto(rs.getDouble("precio_producto"));
                producto.setCantidad_producto(rs.getInt("cantidad_producto"));
                lista.add(producto);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return lista;
    }
    
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, cantidad_producto) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, producto.getNombre_producto());
            ps.setString(2, producto.getDescripcion_producto());
            ps.setDouble(3, producto.getPrecio_producto());
            ps.setInt(4, producto.getCantidad_producto());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre_producto=?, descripcion_producto=?, precio_producto=?, cantidad_producto=? WHERE id_producto=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, producto.getNombre_producto());
            ps.setString(2, producto.getDescripcion_producto());
            ps.setDouble(3, producto.getPrecio_producto());
            ps.setInt(4, producto.getCantidad_producto());
            ps.setInt(5, producto.getId_producto());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarProducto(int id_producto) {
        String sql = "DELETE FROM productos WHERE id_producto=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id_producto);
            ps.executeUpdate();
            return true;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public Producto obtenerProducto(int id_producto) {
        String sql = "SELECT * FROM productos WHERE id_producto=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id_producto);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Producto producto = new Producto();
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre_producto(rs.getString("nombre_producto"));
                    producto.setDescripcion_producto(rs.getString("descripcion_producto"));
                    producto.setPrecio_producto(rs.getDouble("precio_producto"));
                    producto.setCantidad_producto(rs.getInt("cantidad_producto"));
                    return producto;
                }
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}