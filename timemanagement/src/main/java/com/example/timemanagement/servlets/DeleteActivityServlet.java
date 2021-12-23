package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.ActivityDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
import com.example.timemanagement.service.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteActivityServlet", value = "/activity/delete")
public class DeleteActivityServlet extends HttpServlet {
    private final ActivityService activityService;

    public DeleteActivityServlet() throws SQLException {
        this.activityService = new ActivityService(new ActivityDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/deleteActivity.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        activityService.deleteActivity(Integer.parseInt(request.getParameter("activityId")));
        response.sendRedirect("/user/profile");
    }
}
