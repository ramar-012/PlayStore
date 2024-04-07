package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    //database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/application";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ramar123";

    //method to establish database connection
    public static Connection getConnection(){
        Connection connection = null;
        try{
            //load mySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //System.out.println("Connection to the database established successfully!");


        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }


}
