package com.example.timemanagement.service;

import com.example.timemanagement.dao.ActivityDaoImpl;
import com.example.timemanagement.entity.Activity;

import java.sql.SQLException;
import java.util.List;


public class ActivityService {
    private final ActivityDaoImpl activityDao;

    public ActivityService(ActivityDaoImpl activityDao) {
        this.activityDao = activityDao;
    }

    public void saveActivity(Activity activity) {
        activityDao.save(activity);
    }

    public void deleteActivity(int id) {
        activityDao.delete(id);
    }

    public List<Activity> getAll() throws SQLException {
        return activityDao.getAll();
    }

    public List<Activity> getAllFilteredByName() throws SQLException {
        return activityDao.getAllSortByName();
    }

    public List<Activity> getAllFilteredByCategory() throws SQLException {
        return activityDao.getAllOrderByCategory();
    }

    public List<Activity> getAllFilteredByAmountOfUsers() throws SQLException {
        return activityDao.getAllOrderUsersAmount();
    }


}
