package dao;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static UserDAO instance;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sgcl_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Gou3Dusky@";

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public User authenticateUser(String username, String password) {
        User user = new User();
        String query = "SELECT id, nombre, contraseña FROM operador WHERE nombre = ? AND contraseña = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    user.setUsername(resultSet.getString("nombre"));
                    user.setPassword(resultSet.getString("contraseña"));
                    user.setId(resultSet.getInt("id"));
                }
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
    }

}
