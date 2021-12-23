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

@WebServlet(name = "AdminServlet", value = "/admin/profile")
public class AdminServlet extends HttpServlet {
    private final UserService userService;

    public AdminServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.USER)) {
            response.sendRedirect("/user/profile");
        } else {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
