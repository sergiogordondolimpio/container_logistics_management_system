package DAO;

import Model.Container;
import Model.Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sgcl_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Gou3Dusky@";

    public ModuleDAO() {
    }

    public Module save(Module module) {
        String sql = "INSERT INTO modulo (contenedor_id, descripcion, peso, estado, codigo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))  {

            statement.setInt(1, module.getIdContainer());
            statement.setString(2, module.getDescription());
            statement.setFloat(3, module.getWeight());
            statement.setString(4, module.getStatus());
            statement.setString(5, module.getCode());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        module.setId(generatedKeys.getInt(1));
                    }
                }
            }

            return module;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isCodeInDatabase(String code) {
        String sql = "SELECT 1 FROM modulo WHERE codigo = ?";

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

    public List<Module> getModulesByContainer(Integer idContainer) {
        List<Module> modules = new ArrayList<>();
        String sql = "SELECT id, codigo, contenedor_id, estado, descripcion, peso FROM modulo WHERE contenedor_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idContainer);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Module module = new Module();
                    module.setId(resultSet.getInt("id"));
                    module.setCode(resultSet.getString("codigo"));
                    module.setStatus(resultSet.getString("estado"));
                    module.setIdContainer(resultSet.getInt("contenedor_id"));
                    module.setDescription(resultSet.getString("descripcion"));
                    module.setWeight(resultSet.getFloat("peso"));
                    modules.add(module);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }

    public boolean deleteByCode(String code) {
        String sql = "DELETE FROM modulo WHERE codigo = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, code);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void updateHistoryMovementId(Integer idModule, Integer idMovementHistory) {
        String sql = "UPDATE modulo SET historial_movimiento_id = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idMovementHistory);
            statement.setInt(2, idModule);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Successfully updated the historial_movimiento_id for module ID: " + idModule);
            } else {
                System.out.println("No module found with ID: " + idModule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
