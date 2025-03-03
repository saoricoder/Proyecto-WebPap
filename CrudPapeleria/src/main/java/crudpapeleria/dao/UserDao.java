/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudpapeleria.dao;

/**
 *
 * @author User
 */

import crudpapeleria.model.User;
import crudpapeleria.core.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Database db = Database.getInstance();

    // Método para autenticar un usuario
    public User authenticate(String username, String password) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("SELECT id, usuario, contrasena FROM usuarios WHERE usuario = ? AND contrasena = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("usuario"));
                user.setPassword(result.getString("contrasena"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    // Método para registrar un nuevo usuario
    public boolean register(User user) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("INSERT INTO usuarios (usuario, nombre, contrasena) VALUES (?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
    }
}
