<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Add Todo</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/styles.css" />
    <style>
    <%@ include file="/assests/css/styles.css"%>
    </style>
  </head>
<body>
    <div class="container">
        <header>
            <h1>Add New Todo</h1>
        </header>
        <main>
            <!-- Display error message if present -->
            <c:if test="${not empty errorMessage}">
                <div class="error">${errorMessage}</div>
            </c:if>
            <form action="${pageContext.request.contextPath}/AddTodoServlet" method="post" class="form-style">
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" placeholder="Enter todo title" required>
                
                <label for="description">Description:</label>
                <textarea id="description" name="description" placeholder="Enter todo description" rows="4" required></textarea>
                
                <label for="completed" class="checkbox-label">
                    <input type="checkbox" id="completed" name="completed">
                    Mark as Completed
                </label>
                
                <input type="submit" value="Add Todo" class="btn">
            </form>
            <p><a href="${pageContext.request.contextPath}/view/index.jsp">Back to Home</a></p>
        </main>
        <footer>
            <p>&copy; 2025 Todo Application. All Rights Reserved.</p>
        </footer>
    </div>
</body>
</html>
