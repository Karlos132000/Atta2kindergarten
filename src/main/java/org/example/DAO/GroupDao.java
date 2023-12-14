// GroupDao.java
package org.example.DAO;

import org.example.Repository.GroupRepository;
import org.example.db.DatabaseManager;
import org.example.models.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements GroupRepository {

    @Override
    public void save(Group group) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO GroupTable (name, number) VALUES (?, ?)")) {

            statement.setString(1, group.getName());
            statement.setInt(2, group.getNumber());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save Group.");
        }
    }

    @Override
    public Group findById(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM GroupTable WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapGroup(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find Group by ID.");
        }

        return null;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM GroupTable")) {

            while (resultSet.next()) {
                groups.add(mapGroup(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all Groups.");
        }

        return groups;
    }

    @Override
    public void update(Group group) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE GroupTable SET name=?, number=? WHERE id=?")) {

            statement.setString(1, group.getName());
            statement.setInt(2, group.getNumber());
            statement.setInt(3, group.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update Group.");
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM GroupTable WHERE id=?")) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete Group.");
        }
    }

    private Group mapGroup(ResultSet resultSet) throws SQLException {
        return new Group(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("number")
        );
    }
}
