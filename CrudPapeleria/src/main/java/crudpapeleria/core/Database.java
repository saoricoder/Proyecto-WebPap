package crudpapeleria.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private final String DATABASE_NAME = "papeleria";
    private final String DATABASE_USER = "root";
    private final String DATABASE_PASSWORD = "23889";
    
    private static Database instance;
    private final Connection connection;
    private final String connectionString = "jdbc:mysql://localhost:3306/%s";
    
    private Database() throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        connection = DriverManager.getConnection(
                String.format(connectionString, DATABASE_NAME),
                DATABASE_USER,
                DATABASE_PASSWORD
        );
    }
    
    public static Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public ResultSet query(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }
}
