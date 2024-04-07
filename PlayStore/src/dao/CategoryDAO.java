package dao;

import model.Category;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final String INSERT_CATEGORY_SQL = "INSERT INTO category (name) VALUES (?)";
    private static final String SELECT_CATEGORY_BY_ID_SQL = "SELECT * FROM category WHERE category_id=?";
    private static final String SELECT_ALL_CATEGORIES_SQL = "SELECT * FROM category";
    private static final String UPDATE_CATEGORY_SQL = "UPDATE category SET name=? WHERE category_id=?";
    private static final String DELETE_CATEGORY_SQL = "DELETE FROM category WHERE category_id=?";

    public CategoryDAO() {
    }

    //method to get maximum category_id from category table
    public static int getMaxCategoryId() {
        int maxCategoryId = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(category_id) FROM category")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maxCategoryId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxCategoryId;
    }


    // Create category
    public void createCategory(Category category) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get category by ID
    public Category getCategoryById(int category_id) {
        Category category = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID_SQL)) {
            preparedStatement.setInt(1, category_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(category_id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    // Get all categories
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int category_id = resultSet.getInt("category_id");
                String name = resultSet.getString("name");
                Category category = new Category(category_id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Update category
    public void updateCategory(Category category) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getCategoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete category
    public void deleteCategory(int category_id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL)) {
            preparedStatement.setInt(1, category_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
