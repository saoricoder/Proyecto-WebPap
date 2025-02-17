/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import conexion.Database;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author User
 */

public class UsuarioDAO {
    
    public Usuario autenticar(String nombre, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE nombre=? AND contrasena=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    return usuario;
                }
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Usuario obtenerUsuario(int id) {
        String sql = "SELECT * FROM usuarios WHERE id=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    return usuario;
                }
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, contraseña=? WHERE id=?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasena());
            ps.setInt(3, usuario.getId());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
