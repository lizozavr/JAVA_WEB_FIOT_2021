package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.UserDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "LoginUserServlet", value = "/login")
public class LoginUserServlet extends HttpServlet {
    private final UserService userService;

    public LoginUserServlet() throws SQLException {
        userService = new UserService(new UserDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/loginUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        try {
            if (userService.userHasValidUsername(username)) {
                user = userService.login(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!Objects.isNull(user)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/user/profile");
        } else {
            request.setAttribute("error", "Unknown user, please try again");
            response.sendRedirect("/login");
        }
    }
}
