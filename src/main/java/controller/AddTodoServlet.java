package controller;

import model.TodoModel;
import model.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AddTodoServlet")
public class AddTodoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
// 1. Retrieve form data (title, description, completed status)
    	String title = request.getParameter("title");
    	String description = request.getParameter("description");
    	//Checkbox values
    	String completedStr = request.getParameter("completed");
    	boolean completed = "on".equalsIgnoreCase(completedStr) || "true".equalsIgnoreCase(completedStr);
// 2. Create a new TodoModel object with the form data
    	TodoModel todo = new TodoModel(title,description, completed);
// 3. Add the todo to the database using TodoDAO
    	try {
    		TodoDAO.addTodo(todo);
    		response.sendRedirect(request.getContextPath()+ "/ListTodoServlet");
    	} catch (SQLException e) {
    		e.printStackTrace();
    		request.setAttribute("errorMessage", "Failed to add todo: " + e.getMessage());
    		request.getRequestDispatcher("/view/add-todo.jsp").forward(request, response);
    	}
// 4. Redirect to the list page after successful addition or show error message
    }
}
