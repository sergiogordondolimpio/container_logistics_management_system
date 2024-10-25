package dao;

import model.Container;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class ContainerDAO extends BaseDAO implements StorageUnitDAO<Container> {

    public ContainerDAO() {}

    public Container save(Container container) {
        String sql = "INSERT INTO contenedor (ubicacion, estado, codigo, fecha_llegada) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {

            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

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
        } finally {
            closeConnection(conn);
        }

        return null;
    }


    // Return true if the code exist in database
    public boolean isCodeInDatabase(String code) {
        String sql = "SELECT 1 FROM contenedor WHERE codigo = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return false;
    }

    // Get all containers from the database
    public List<Container> getAllContainers() {
        List<Container> containers = new ArrayList<>();
        String sql = "SELECT id, codigo, ubicacion, estado, fecha_llegada FROM contenedor";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

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
        } finally {
            closeConnection(conn);
        }

        return containers;
    }

    public Integer getIdContainerByCode(String code) {
        String sql = "SELECT id FROM contenedor WHERE codigo = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return -1;
    }

    public Container getContainerByCode(String code) {
        Container container = new Container();
        String sql = "SELECT codigo, ubicacion, id, estado, fecha_llegada FROM contenedor WHERE codigo = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

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
        } finally {
            closeConnection(conn);
        }

        return container;
    }
}

