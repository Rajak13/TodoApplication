<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="java.util.List, model.TodoModel" %>
<html>
  <head>
    <title>Todo List</title>
    <style>
      <%@ include file="/assests/css/styles.css"%>
    </style>
  </head>
  <body>
    <div class="container">
        <header>
            <h1>Todo List</h1>
        </header>
        <main>
            <table class="styled-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Completed</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<TodoModel> todos = (List<TodoModel>) request.getAttribute("todos");
                        if (todos != null && !todos.isEmpty()) {
                            for (TodoModel todo : todos) {
                    %>
                        <tr>
                            <td><%= todo.getId() %></td>
                            <td><%= todo.getTitle() %></td>
                            <td><%= todo.getDescription() %></td>
                            <td><%= todo.isCompleted() ? "Yes" : "No" %></td>
                            <td>
                            <a href="${pageContext.request.contextPath}/UpdateTodoServlet?id=<%= todo.getId() %>&completed=<%= todo.isCompleted() %>" class="btn-update">
                                    <%= todo.isCompleted() ? "Mark Incomplete" : "Mark Complete" %>
                                </a>
                                <a href="${pageContext.request.contextPath}/DeleteTodoServlet?id=<%= todo.getId() %>" class="btn-delete">Delete</a>
                            </td>
                        </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="5">No todos found. Please add a new todo.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <p><a href="${pageContext.request.contextPath}/view/index.jsp">Back to Home</a></p>
        </main>
        <footer>
            <p>&copy; 2025 Todo Application. All Rights Reserved.</p>
        </footer>
    </div>
</body>
</html>
