package DAO;

import Model.Container;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class ContainerDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sgcl_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Gou3Dusky@";

    public ContainerDAO() {}

    public Container save(Container container) {
        String sql = "INSERT INTO contenedor (ubicacion, estado, codigo, fecha_llegada) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, container.getLocation());
            statement.setString(2, container.getStatus());
            statement.setString(3, container.getCode());

            LocalDateTime arriveDate = container.getArriveDate();
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(arriveDate));

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        container.setId(generatedKeys.getInt(1));
                    }
                }
            }

            return container;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Return true if the code exist in database
    public boolean siCodeInDatabase(String code) {
        String sql = "SELECT 1 FROM contenedor WHERE codigo = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Get all containers from the database
    public List<Container> getAllContainers() {
        List<Container> containers = new ArrayList<>();
        String sql = "SELECT id, codigo, ubicacion, estado, fecha_llegada FROM contenedor";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {


            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Container container = new Container();
                    container.setCode(resultSet.getString("codigo"));
                    container.setLocation(resultSet.getString("ubicacion"));
                    container.setId(resultSet.getInt("id"));
                    container.setStatus(resultSet.getString("estado"));
                    container.setArriveDate(resultSet.getDate("fecha_llegada"));
                    containers.add(container);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return containers;
    }

    public Integer getIdContainerByCode(String code) {
        String sql = "SELECT id FROM contenedor WHERE codigo = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void updateHistoryMovementId(Integer containerId, Integer idMovementHistory) {
        String sql = "UPDATE contenedor SET historial_movimiento_id = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idMovementHistory);
            statement.setInt(2, containerId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Successfully updated the historial_movimiento_id for container ID: " + containerId);
            } else {
                System.out.println("No container found with ID: " + containerId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Container getContainerByCode(String code) {
        Container container = new Container();
        String sql = "SELECT codigo, ubicacion, id, estado, fecha_llegada FROM contenedor WHERE codigo = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    container.setCode(resultSet.getString("codigo"));
                    container.setLocation(resultSet.getString("ubicacion"));
                    container.setId(resultSet.getInt("id"));
                    container.setStatus(resultSet.getString("estado"));
                    container.setArriveDate(resultSet.getTimestamp("fecha_llegada").toLocalDateTime());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return container;
    }
}

