package DAO;

import Manager.SessionManager;

import java.sql.*;
import java.time.LocalDateTime;

public class MovementHistoryDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sgcl_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Gou3Dusky@";

    public MovementHistoryDAO() {}

    // Return the id of the MovementHistory created in database
    public Integer save(String type) {
        String sql = "INSERT INTO historial_movimiento (operador_id, fecha, tipo) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, SessionManager.getInstance().getLoggedInUser().getId());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, type);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}

