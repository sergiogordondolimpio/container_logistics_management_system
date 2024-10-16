package DAO;

import Model.Container;

import java.sql.*;
import java.time.LocalDateTime;

public class ContainerDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sgcl_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Gou3Dusky@";

    public ContainerDAO() {}

    public Container save(Container container) {
//        String sql = "INSERT INTO contenedor (id, ubicacion, estado, codigo, fecha_llegada) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, container.getIdContainer());
//            statement.setString(2, container.getLocation());
//            statement.setString(3, container.getStatus());
//            statement.setString(4, container.getCode());
//
//            LocalDateTime arriveDate = container.getArriveDate();
//            statement.setTimestamp(5, java.sql.Timestamp.valueOf(arriveDate));
//
//            statement.executeUpdate();
//
//            return container;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;

        // To do: for the database is not conected
        return container;
    }

    // Return true if the code exist in database
    public boolean siCodeInDatabase(String code) {
//        String sql = "SELECT 1 FROM contenedor WHERE codigo = ?";
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setString(1, code);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return true;
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;

        // To do: for the database is not conected
        return false;
    }
}

