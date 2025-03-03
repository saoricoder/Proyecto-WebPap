package crudpapeleria.dao;

import crudpapeleria.model.Cliente;
import crudpapeleria.core.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private final Database db = Database.getInstance();

    // Método para obtener todos los clientes
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (ResultSet result = db.query(sql)) {
            while (result.next()) {
                Cliente cliente = new Cliente(
                    result.getString("numero_identificacion"),
                    result.getString("nombre"),
                    result.getString("direccion"),
                    result.getString("telefono"),
                    result.getString("correo")
                );
                cliente.setId(result.getInt("id")); // Asignar el ID del cliente
                clientes.add(cliente); // Agregar a la lista
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener clientes: " + ex.getMessage());
        }
        return clientes;
    }

    // Método para registrar un cliente
    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (numero_identificacion, nombre, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setString(1, cliente.getNumeroIdentificacion());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getDireccion());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getCorreo());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al registrar cliente: " + ex.getMessage());
            return false;
        }
    }

    // Método para obtener un cliente por su ID
    public Cliente getClienteById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Cliente cliente = new Cliente(
                    result.getString("numero_identificacion"),
                    result.getString("nombre"),
                    result.getString("direccion"),
                    result.getString("telefono"),
                    result.getString("correo")
                );
                cliente.setId(result.getInt("id")); // Asignar el ID del cliente
                return cliente;
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener cliente por ID: " + ex.getMessage());
        }
        return null;
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET numero_identificacion = ?, nombre = ?, direccion = ?, telefono = ?, correo = ? WHERE id = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setString(1, cliente.getNumeroIdentificacion());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getDireccion());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getCorreo());
            statement.setInt(6, cliente.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar cliente: " + ex.getMessage());
            return false;
        }
    }

    // Método para eliminar un cliente
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar cliente: " + ex.getMessage());
            return false;
        }
    }
}