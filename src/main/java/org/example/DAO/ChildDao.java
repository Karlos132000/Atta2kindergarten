// ChildDao.java
package org.example.DAO;

import org.example.Repository.ChildRepository;
import org.example.db.DatabaseManager;
import org.example.models.Child;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChildDao implements ChildRepository {
    private int getId; // Variable declaration

    @Override
    public void save(Child child) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Child (fullName, gender, age) VALUES (?, ?, ?)")) {

            statement.setString(1, child.getFullName());
            statement.setString(2, child.getGender());
            statement.setInt(3, child.getAge());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save Child.");
        }
    }

    @Override
    public Child findById(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Child WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapChild(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find Child by ID.");
        }

        return null;
    }

    @Override
    public List<Child> findAll() {
        List<Child> children = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Child")) {

            while (resultSet.next()) {
                children.add(mapChild(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all Children.");
        }

        return children;
    }

    @Override
    public void update(Child child) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Child SET fullName=?, gender=?, age=? WHERE id=?")) {

            statement.setString(1, child.getFullName());
            statement.setString(2, child.getGender());
            statement.setInt(3, child.getAge());
            statement.setInt(4, child.getId(getId));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update Child.");
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Child WHERE id=?")) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete Child.");
        }
    }

    @Override
    public List<Child> getAllChildren() {
        return null;
    }

    private Child mapChild(ResultSet resultSet) throws SQLException {
        return new Child(
                resultSet.getInt("id"),
                resultSet.getString("fullName"),
                resultSet.getString("gender"),
                resultSet.getInt("age")
        );
    }
}
