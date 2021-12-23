package com.example.timemanagement.service;

import com.example.timemanagement.dao.UserDaoImpl;
import com.example.timemanagement.entity.Activity;
import com.example.timemanagement.entity.User;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class UserService {
    private final UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    public void setActivityToUser(int userId, int activityId) throws SQLException {
        userDao.setActivityToUser(userId, activityId);
    }

    public void setActivityTime(int userId, int activityId, Time time) throws SQLException {
        userDao.setActivityTime(userId, activityId, time);
    }

    public List<Activity> getUserActivities(int userId) throws SQLException {
        return userDao.getUserActivities(userId);
    }

    public boolean userHasValidUsername(String username) throws SQLException {
        return userDao.isUsernameValid(username);
    }

//    public boolean userHasValidPassword(String password){
//
//    }
}
