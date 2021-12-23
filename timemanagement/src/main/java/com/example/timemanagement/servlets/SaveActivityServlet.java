package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.ActivityDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
import com.example.timemanagement.entity.Activity;
import com.example.timemanagement.service.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "SaveActivityServlet", value = "/activity/save")
public class SaveActivityServlet extends HttpServlet {

    private final ActivityService activityService;

    public SaveActivityServlet() throws SQLException {
        this.activityService = new ActivityService(new ActivityDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createActivity.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("activityName");
        String category = request.getParameter("activityCategory");
        activityService.saveActivity(new Activity(1, name, category));
        response.sendRedirect("/user/profile");

    }
}
