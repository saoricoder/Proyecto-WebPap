package crudpapeleria.dao;

import crudpapeleria.core.Database;
import crudpapeleria.model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private final Database db = Database.getInstance();

    // Obtener todas las categorías
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("SELECT id_categoria, nombre_categoria FROM categorias");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Category category = new Category(result.getInt("id_categoria"), result.getString("nombre_categoria"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return categories;
    }

    // Crear una nueva categoría
    public boolean createCategory(Category category) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("INSERT INTO categorias (nombre_categoria) VALUES (?)");
            statement.setString(1, category.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
    }

    public boolean updateCategory(Category category) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("UPDATE categorias SET nombre_categoria = ? WHERE id_categoria = ?");
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
    }

    public boolean deleteCategory(int id) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("DELETE FROM categorias WHERE id_categoria = ?");
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
    }

    // Obtener una categoría por su ID
    public Category getCategory(int id) {
        try {
            PreparedStatement statement = db.getConnection()
                    .prepareStatement("SELECT id_categoria, nombre_categoria FROM categorias WHERE id_categoria = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Category category = new Category(result.getInt("id_categoria"), result.getString("nombre_categoria"));
                return category;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return null; // Retorna null si no encuentra la categoría
    }
}
