package dao;
import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {

    //SQL Queries
    private static final String INSERT_USER_SQL = "INSERT INTO User (username, password, role) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM User WHERE user_id = ?;";
    private static final String UPDATE_USER_SQL = "UPDATE User SET username = ?, password = ?, role = ? WHERE user_id = ?;";
    private static final String DELETE_USER_SQL = "DELETE FROM User WHERE user_id = ?;";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM User;";
    private static final String SELECT_USER_BY_USERNAME_SQL = "SELECT * FROM User WHERE username = ? AND password = ?;";

    //get max of user_id
    public int getMaxUserId() {
        int maxUserId = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(user_id) FROM User;")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                maxUserId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxUserId;
    }

    //get role of user
    public String getUserRole(String username) {
        String role = "";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM User WHERE username = ?;")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    //Create a new user
    public void createUser(User user) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            if(checkUserExist(user.getUsername())){
                System.out.println("User already exists with the same username. Please try again with new credentials!.");
            }
            else{
                preparedStatement.executeUpdate();
                System.out.println("User " + user.getUsername() + " has been created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    //get a user by ID
    public User getUserById(int user_id) {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                user = new User(user_id, username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //Update a user
    public void updateUser(User user) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete a user
    public void deleteUser(int user_id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();
            System.out.println("User " + user_id + " has been deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(user_id, username, password, role);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    //check user credentials for login
    public boolean checkUserCredentials(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //check if user already exist by name avoiding case-sensitive
    public boolean checkUserExist(String username) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE LOWER(username) = LOWER(?);")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
