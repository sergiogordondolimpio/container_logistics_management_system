package dao;

import utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {

    // Get connection
    protected Connection getConnection() {
        Connection connection = null;
        try {
            String url = Config.get("db.url");
            String user = Config.get("db.user");
            String password = Config.get("db.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al establecer la conexión con la base de datos.", e);
        }
        return connection;
    }

    // Close connection
    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

