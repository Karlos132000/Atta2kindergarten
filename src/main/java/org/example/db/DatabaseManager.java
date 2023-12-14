package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/kindergarden";
    private static final String USER = "root";
    private static final String PASSWORD = "2004";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.");
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static void createTables() {
        try (Connection connection = getConnection()) {
            // Disable autocommit
            connection.setAutoCommit(false);

            createChildTable(connection);
            createGroupTable(connection);

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create tables.");
        }
    }

    private static void createChildTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Child (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "fullName VARCHAR(255) NOT NULL," +
                    "gender VARCHAR(10) NOT NULL," +
                    "age INT NOT NULL)";

            statement.execute(sql);
            System.out.println("Child table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Child table. Error: " + e.getMessage());
        }
    }

    private static void createGroupTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS GroupTable (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "number INT NOT NULL)";

            statement.execute(sql);
            System.out.println("Group table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Group table. Error: " + e.getMessage());
        }
    }
}
