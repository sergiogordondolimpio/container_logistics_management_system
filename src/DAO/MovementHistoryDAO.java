package DAO;

import Manager.SessionManager;
import Model.Module;
import Model.MovementHistory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovementHistoryDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sgcl_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Gou3Dusky@";

    public MovementHistoryDAO() {}

    // Return the id of the MovementHistory created in database
    public Integer save(String type, Integer idItem, String typeItem) {
        String sql = "INSERT INTO historial_movimiento (operador_id, fecha, tipo, item_id, item_type) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, SessionManager.getInstance().getLoggedInUser().getId());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, type);
            statement.setInt(4, idItem);
            statement.setString(5, typeItem);


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<MovementHistory> getHistoryByIdItem(Integer idItem, String itemType) {
            List<MovementHistory> movements = new ArrayList<>();
        String sql = "SELECT id, operador_id, fecha, tipo, item_id, item_type FROM historial_movimiento WHERE item_id = ? and item_type = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idItem);
            statement.setString(2, itemType);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MovementHistory movement = new MovementHistory();
                    movement.setId((resultSet.getInt("id")));
                    movement.setIdOperator(resultSet.getInt("operador_id"));
                    movement.setDate(resultSet.getTimestamp("fecha").toLocalDateTime());
                    movement.setType(resultSet.getString("tipo"));
                    movement.setIdItem(resultSet.getInt("item_id"));
                    movement.setItemType(resultSet.getString("item_type"));
                    movements.add(movement);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movements;
    }
}

