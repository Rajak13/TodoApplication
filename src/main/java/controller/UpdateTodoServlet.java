package controller;

import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateTodoServlet")
public class UpdateTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the todo id and current status from request parameters
        String idStr = request.getParameter("id");
        String completedStr = request.getParameter("completed");
        if (idStr != null && completedStr != null) {
            int id = Integer.parseInt(idStr);
            boolean currentStatus = Boolean.parseBoolean(completedStr);
            // Toggle the status: if it's currently true, set to false and vice versa
            boolean newStatus = !currentStatus;
            try {
                // Update the todo's completed status using the DAO method
                TodoDAO.updateTodoCompleted(id, newStatus);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error updating todo: " + e.getMessage());
            }
        }
        // Redirect back to the list page after updating
        response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
    }
}
