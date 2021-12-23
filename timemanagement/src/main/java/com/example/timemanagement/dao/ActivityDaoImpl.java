package com.example.timemanagement.dao;


import com.example.timemanagement.entity.Activity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityDaoImpl {
    private final Connection connection;

    public ActivityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(Activity activity) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into activities (name, category) values (?,?)"
        )) {
            statement.setString(1, activity.getName());
            statement.setString(2, activity.getCategory());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Activity> getById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from activities where id=" + id + ";");
        Activity activity = new Activity();
        if (resultSet.next()) {
            activity.setId(resultSet.getInt("id"));
            activity.setName(resultSet.getString("name"));
            activity.setCategory(resultSet.getString("category"));
        }
        return Optional.ofNullable(activity);
    }

    public void delete(int id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("delete from activities where id = '" + id + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Activity findById(int activityId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM activities WHERE id=" + activityId + ";");
        Activity activity = new Activity();
        if (resultSet.next()) {
            activity.setId(resultSet.getInt("id"));
            activity.setName(resultSet.getString("name"));
            activity.setCategory(resultSet.getString("category"));
        }
        return activity;
    }

    public List<Activity> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from activities;");
        List<Activity> result = new ArrayList<>();
        while (resultSet.next()) {
            Activity activity = new Activity();
            activity.setId(resultSet.getInt("id"));
            activity.setName(resultSet.getString("name"));
            activity.setCategory(resultSet.getString("category"));
            result.add(activity);
        }
        return result;
    }

    public List<Activity> getAllSortByName() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from activities order by name;");
        List<Activity> result = new ArrayList<>();
        while (resultSet.next()) {
            Activity activity = new Activity();
            activity.setId(resultSet.getInt("id"));
            activity.setName(resultSet.getString("name"));
            activity.setCategory(resultSet.getString("category"));
            result.add(activity);
        }
        return result;
    }

    public List<Activity> getAllOrderByCategory() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from activities order by category;");
        List<Activity> result = new ArrayList<>();
        while (resultSet.next()) {
            Activity activity = new Activity();
            activity.setId(resultSet.getInt("id"));
            activity.setName(resultSet.getString("name"));
            activity.setCategory(resultSet.getString("category"));
            result.add(activity);
        }
        return result;
    }

    public List<Activity> getAllOrderUsersAmount() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select ac.id, ac.name, ac.category, COUNT(*) as acCount from activities ac join user_activity ua on ac.id = ua.activity_id\n" +
                "group by ac.id\n" +
                "order by acCount DESC;");
        List<Activity> result = new ArrayList<>();
        while (resultSet.next()) {
            Activity activity = new Activity();
            activity.setId(resultSet.getInt("id"));
            activity.setName(resultSet.getString("name"));
            activity.setCategory(resultSet.getString("category"));
            result.add(activity);
        }
        return result;
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
