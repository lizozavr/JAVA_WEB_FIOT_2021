package com.example.timemanagement.servlets;

import com.example.timemanagement.dao.ActivityDaoImpl;
import com.example.timemanagement.db.DatabaseConnection;
import com.example.timemanagement.entity.Role;
import com.example.timemanagement.entity.User;
import com.example.timemanagement.service.ActivityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetAllActivitiesServlet", value = "/admin/activity")
public class GetAllActivitiesServlet extends HttpServlet {
    private final ActivityService activityService;

    public GetAllActivitiesServlet() throws SQLException {
        activityService = new ActivityService(new ActivityDaoImpl(DatabaseConnection.getDBConnection().getConnection()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.ADMIN)) {
            try {
                request.setAttribute("activities", activityService.getAll());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.getRequestDispatcher("/activities.jsp").forward(request, response);
        } else {
            response.sendRedirect("/user/profile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
