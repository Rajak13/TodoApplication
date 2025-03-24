package model;

import java.sql.*;
import java.util.ArrayList;

public class TodoDAO {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/todo_database";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Replace with your MySQL password

    // Load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }

    // Establish a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to add a new todo to the database
    public static int addTodo(TodoModel todoModel) throws SQLException {
        String sql = "INSERT INTO todo_table (title, description, completed) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, todoModel.getTitle());
            ps.setString(2, todoModel.getDescription());
            ps.setBoolean(3, todoModel.isCompleted());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Adding todo failed, no rows affected.");
            }
            // Retrieve generated ID for the new todo
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    todoModel.setId(id);
                    return id;
                } else {
                    throw new SQLException("Adding todo failed, no ID obtained.");
                }
            }
        }
    }

    //Methof to update the status of a todo
    public static boolean updateTodoCompleted(int id, boolean completed) throws SQLException {
    	String sql = "UPDATE todo_table SET completed = ? where id = ?";
    	try(Connection conn = getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
    				ps.setBoolean(1, completed);
    				ps.setInt(2, id);
    				int affectedRows = ps.executeUpdate();
    				return affectedRows > 0;
    			}
    }
    // Method to remove a todo from the database using its ID
    public static boolean removeTodo(int id) throws SQLException {
        String sql = "DELETE FROM todo_table WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Method to list all todos from the database
    public static ArrayList<TodoModel> listTodo() {
        ArrayList<TodoModel> todos = new ArrayList<>();
        String sql = "SELECT * FROM todo_table";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");
                todos.add(new TodoModel(id, title, description, completed));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }
}
