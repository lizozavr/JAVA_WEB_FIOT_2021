package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.UserDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
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

@WebServlet(name = "UserServlet", value = "/user/profile")
public class UserServlet extends HttpServlet {
    private final UserService userService;

    public UserServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.ADMIN)) {
            response.sendRedirect("/admin/profile");
        } else {
            request.setAttribute("user", user);
            try {
                request.setAttribute("activities", userService.getUserActivities(user.getId()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
