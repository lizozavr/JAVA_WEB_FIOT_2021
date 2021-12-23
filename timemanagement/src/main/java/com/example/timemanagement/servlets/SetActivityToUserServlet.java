package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.UserDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
import com.example.timemanagement.entity.Activity;
import com.example.timemanagement.entity.Role;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SetActivityToUserServlet", value = "/admin/setActivity")
public class SetActivityToUserServlet extends HttpServlet {
    private final UserService userService;

    public SetActivityToUserServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.ADMIN)) {
            request.getRequestDispatcher("/setActivityToUser.jsp").forward(request, response);
        } else {
            response.sendRedirect("/user/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int activityId = Integer.parseInt(request.getParameter("activityId"));
            userService.setActivityToUser(userId, activityId);
            response.sendRedirect("/user/profile");
        } catch (SQLException throwables) {
            response.sendRedirect("/admin/setActivity");
        }
    }
}
