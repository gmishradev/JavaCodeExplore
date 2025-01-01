package com.warehouse;

import java.sql.*;

public class ExecuteStoredProcedure {
    public static void main(String[] args) {
        // JDBC URL, username, and password of the database
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "username";
        String password = "password";

        // Establishing Connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Prepare the stored procedure call
            CallableStatement callableStatement = connection.prepareCall("{call your_stored_procedure(?, ?)}");

            // Set the parameters (if any)
            callableStatement.setString(1, "parameter1");
            callableStatement.setInt(2, 123);

            // Execute the stored procedure
            callableStatement.execute();

            // Process the result (if any)
            // For example, if your stored procedure returns a ResultSet
            try (ResultSet resultSet = callableStatement.getResultSet()) {
                while (resultSet.next()) {
                    // Process each row of the result set
                    // Example: String result = resultSet.getString("column_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
