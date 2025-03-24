package controller;

import model.TodoModel;
import model.TodoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ListTodoServlet")
public class ListTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Retrieve all todos from the database using TodoDAO
        ArrayList<TodoModel> todos = TodoDAO.listTodo();
        // 2. Set the todos as a request attribute to be accessed in the JSP
        request.setAttribute("todos", todos);
        // 3. Forward the request to the list-todo.jsp page for display
        request.getRequestDispatcher("/view/list-todo.jsp").forward(request, response);
    }
}
