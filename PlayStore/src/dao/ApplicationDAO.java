package dao;

import model.Application;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {
    private static final String INSERT_APPLICATION_SQL = "INSERT INTO application (name, description, release_date, version, ratings, genre) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_APPLICATION_BY_ID_SQL = "SELECT * FROM application WHERE app_id=?";
    private static final String SELECT_ALL_APPLICATIONS_SQL = "SELECT * FROM application";
    private static final String UPDATE_APPLICATION_SQL = "UPDATE application SET name=?, description=?, release_date=?, version=?, ratings=?, genre=? WHERE app_id=?";
    private static final String DELETE_APPLICATION_SQL = "DELETE FROM application WHERE app_id=?";

    public ApplicationDAO() {
    }

    //get maximum app_id
    public int getMaxAppId() {
        int maxAppId = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(app_id) FROM application")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maxAppId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxAppId;
    }

    // Create application
    public void createApplication(Application application) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPLICATION_SQL)) {
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getDescription());
            preparedStatement.setString(3, application.getReleaseDate());
            preparedStatement.setString(4, application.getVersion());
            preparedStatement.setDouble(5, application.getRatings());
            preparedStatement.setString(6, application.getGenre());
            if(checkAppExist(application.getName())){
                System.out.println("Application already exists with the same name. Please try again with new application name!.");
            }else{
                preparedStatement.executeUpdate();
                System.out.println("Application: " + application.getName() + " created successfully");
                addGenreToCategory(application.getGenre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Application: " + application.getName() + " creation failed due to" + e.getMessage() + " error!;");
        }
    }

    // Get application by ID
    public Application getApplicationById(int app_id) {
        Application application = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPLICATION_BY_ID_SQL)) {
            preparedStatement.setInt(1, app_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String releaseDate = resultSet.getString("release_date");
                String version = resultSet.getString("version");
                double ratings = resultSet.getDouble("ratings");
                String genre = resultSet.getString("genre");
                application = new Application(app_id, name, description, releaseDate, version, ratings, genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }

    // Get all applications
    public List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPLICATIONS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int app_id = resultSet.getInt("app_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String releaseDate = resultSet.getString("release_date");
                String version = resultSet.getString("version");
                double ratings = resultSet.getDouble("ratings");
                String genre = resultSet.getString("genre");
                Application application = new Application(app_id, name, description, releaseDate, version, ratings, genre);
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // Update application
    public void updateApplication(Application application) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APPLICATION_SQL)) {
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getDescription());
            preparedStatement.setString(3, application.getReleaseDate());
            preparedStatement.setString(4, application.getVersion());
            preparedStatement.setDouble(5, application.getRatings());
            preparedStatement.setString(6, application.getGenre());
            preparedStatement.setInt(7, application.getAppId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete application
    public void deleteApplication(int app_id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_APPLICATION_SQL)) {
            preparedStatement.setInt(1, app_id);
            if(checkAppExistById(app_id)){
                preparedStatement.executeUpdate();
                System.out.println("Application with ID: " + app_id + " deleted successfully!");
            } else{
                System.out.println("Application with ID: " + app_id + " doesn't exist in the database. " +
                        "Please try again with valid application ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //search an app by its name: match all case
    public List<Application> searchApplicationByName(String name) {
        List<Application> applications = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM application WHERE UPPER(name) LIKE UPPER(?)")) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int app_id = resultSet.getInt("app_id");
                String appName = resultSet.getString("name");
                String description = resultSet.getString("description");
                String releaseDate = resultSet.getString("release_date");
                String version = resultSet.getString("version");
                double ratings = resultSet.getDouble("ratings");
                String genre = resultSet.getString("genre");
                Application application = new Application(app_id, appName, description, releaseDate, version, ratings, genre);
                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    //get applications by its genre
    public List<Application> searchApplicationByGenre(String genre) {
        List<Application> applicationsbyGenre = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM application WHERE UPPER(genre) LIKE UPPER(?)")) {
            preparedStatement.setString(1, "%" + genre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int app_id = resultSet.getInt("app_id");
                String appName = resultSet.getString("name");
                String description = resultSet.getString("description");
                String releaseDate = resultSet.getString("release_date");
                String version = resultSet.getString("version");
                double ratings = resultSet.getDouble("ratings");
                String appGenre = resultSet.getString("genre");
                Application application = new Application(app_id, appName, description, releaseDate, version, ratings, appGenre);
                applicationsbyGenre.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicationsbyGenre;
    }

    /*Method to add the genre given in createApplication method to the category table in the database.
    Check if that genre in the 'name' column doesn't exist then automatically add it to the category table.
     */
    public void addGenreToCategory(String genre) {
        //get max category_id from the category table
        int maxId = CategoryDAO.getMaxCategoryId();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO category (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM category WHERE name = ?)")) {
            preparedStatement.setString(1, genre);
            preparedStatement.setString(2, genre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //check if app already exist by name avoiding case-sensitive
    public boolean checkAppExist(String name) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM application WHERE LOWER(name) = LOWER(?);")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    //check if an app already exist by its ID
    public boolean checkAppExistById(int app_id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM application WHERE app_id = ?;")) {
            preparedStatement.setInt(1, app_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}