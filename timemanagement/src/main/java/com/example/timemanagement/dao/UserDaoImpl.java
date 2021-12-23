package com.example.timemanagement.dao;


import com.example.timemanagement.entity.Activity;
import com.example.timemanagement.entity.Role;
import com.example.timemanagement.entity.User;

import java.sql.*;
import java.util.*;

public class UserDaoImpl {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public User login(String username, String password) {
        int userId = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM user WHERE username= '" + username + "'AND password= '" + password + "';");
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
            return findById(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User findById(int userId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id=" + userId + ";");
        User user = new User();
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(Role.valueOf(resultSet.getString("role")));
            return user;
        }
        return null;
    }

    public Activity findActivityById(int activityId) throws SQLException {
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

    public void setActivityToUser(int userId, int activityId) throws SQLException {
        if (findById(userId) != null && findActivityById(activityId).getName() != null) {
            PreparedStatement statement = connection.prepareStatement("insert into user_activity (user_id, activity_id, time) VALUES (?, ?, null);");
            statement.setString(1, String.valueOf(userId));
            statement.setString(2, String.valueOf(activityId));
            statement.executeUpdate();
        }
    }

    public void setActivityTime(int userId, int activityId, Time time) throws SQLException {
        if (findById(userId) != null && findActivityById(activityId).getName() != null) {
            PreparedStatement statement = connection.prepareStatement("update user_activity set time ='" + time
                    + "' where (user_id = " + userId + " and activity_id = " + activityId + ");");
            statement.executeUpdate();
        }
    }

    public List<Activity> getUserActivities(int userId) throws SQLException {
        if (findById(userId).getUsername() != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user_activity where user_id in (" + userId + ");");
            List<Integer> activityIds = new ArrayList<>();
            while (resultSet.next()) {
                int activityId = resultSet.getInt("activity_id");
                activityIds.add(activityId);
            }
            List<Activity> result = new ArrayList<>();
            for (int id : activityIds) {
                Activity activity = new Activity();
                ResultSet activitiesResultSet = statement.executeQuery("select * from activities where id =" + id + ";");
                if (activitiesResultSet.next()) {
                    activity.setId(activitiesResultSet.getInt("id"));
                    activity.setName(activitiesResultSet.getString("name"));
                    activity.setCategory(activitiesResultSet.getString("category"));
                }
                result.add(activity);
            }
            return result;
        }
        return null;
    }

    public boolean isUsernameValid(String username) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where username = '" + username + "';");
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(Role.valueOf(resultSet.getString("role")));
        }
        return user != null;
    }

//    public boolean isUserCredsValid(String username, string password)


}