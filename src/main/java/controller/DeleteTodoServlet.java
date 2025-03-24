package controller;

import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Retrieve the todo ID from the request parameter
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            try {
                // 2. Delete the todo from the database using TodoDAO
                TodoDAO.removeTodo(id);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error deleting todo: " + e.getMessage());
            }
        }
        // 3. Redirect to the list page after deletion
        response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
    }
}
