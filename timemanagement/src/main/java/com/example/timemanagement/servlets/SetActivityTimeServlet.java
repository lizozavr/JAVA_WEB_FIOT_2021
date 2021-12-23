package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.UserDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
import com.example.timemanagement.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "SetActivityTimeServlet", value = "/setActivityTime")
public class SetActivityTimeServlet extends HttpServlet {
    private final UserService userService;

    public SetActivityTimeServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/setActivityTime.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int activityId = Integer.parseInt(request.getParameter("activityId"));
        Time time = Time.valueOf(request.getParameter("time"));
        try {
            userService.setActivityTime(userId, activityId, time);
            response.sendRedirect("/user/profile");
        } catch (SQLException throwables) {
            response.sendRedirect("/setActivityTime");
        }
    }
}
