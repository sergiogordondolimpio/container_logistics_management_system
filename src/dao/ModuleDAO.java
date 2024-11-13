package dao;

import model.Module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAO extends BaseDAO implements StorageUnitDAO<Module> {

    public ModuleDAO() {
    }

    public Module save(Module module) {
        String sql = "INSERT INTO modulo (contenedor_id, descripcion, peso, estado, codigo) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

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
        } finally {
            closeConnection(conn);
        }

        return null;
    }

    public boolean isCodeInDatabase(String code) {
        String sql = "SELECT 1 FROM modulo WHERE codigo = ?";
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

    public List<Module> getModulesByContainer(Integer idContainer) {
        List<Module> modules = new ArrayList<>();
        String sql = "SELECT id, codigo, contenedor_id, estado, descripcion, peso FROM modulo WHERE contenedor_id = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

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
        } finally {
            closeConnection(conn);
        }

        return modules;
    }

    public boolean deleteByCode(String code) {
        String sql = "DELETE FROM modulo WHERE codigo = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, code);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return false;
    }

    public Module updateModuleByCode(Module module) {
        String sql = "UPDATE modulo SET descripcion = ?, estado = ?, peso = ? WHERE codigo = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement updateStatement = conn.prepareStatement(sql);

            // Set values for columns to be updated
            updateStatement.setString(1, module.getDescription());
            updateStatement.setString(2, module.getStatus());
            updateStatement.setFloat(3, module.getWeight());
            updateStatement.setString(4, module.getCode());

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Reuse getModuleByCode to fetch the updated module data
                return getModuleByCode(module.getCode());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return null;
    }

    public Module getModuleByCode(String code) {
        String sql = "SELECT id, codigo, contenedor_id, estado, descripcion, peso FROM modulo WHERE codigo = ?";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, code);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Module module = new Module();
                    module.setId(resultSet.getInt("id"));
                    module.setCode(resultSet.getString("codigo"));
                    module.setStatus(resultSet.getString("estado"));
                    module.setIdContainer(resultSet.getInt("contenedor_id"));
                    module.setDescription(resultSet.getString("descripcion"));
                    module.setWeight(resultSet.getFloat("peso"));
                    return module;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return null;
    }


}
