<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Todo Application</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/styles.css" />
    <style>
    <%@ include file="/assests/css/styles.css"%>
    </style>
  </head>
  <body>
    <div class="container">
        <header>
            <h1>Todo Application</h1>
        </header>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/view/add-todo.jsp">Add Todo</a></li>
                <li><a href="${pageContext.request.contextPath}/ListTodoServlet">View Todos</a></li>
            </ul>
        </nav>
        <main>
            <p>Welcome! Use the navigation links above to manage your todos.</p>
        </main>
        <footer>
            <p>&copy; 2025 Todo Application. All Rights Reserved.</p>
        </footer>
    </div>
</body>
</html>
