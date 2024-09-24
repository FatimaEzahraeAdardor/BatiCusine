package Repository.Implementation;

import Config.DataBaseConnection;
import Entities.Labor;
import Entities.Material;
import Entities.Project;
import Repository.Interface.MaterialInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaterialRepository implements MaterialInterface {
    private Connection connection;

    public MaterialRepository() {
        this.connection = DataBaseConnection.getInstance().getConnection();
    }
    @Override
    public Material save(Material material) {
        String query = "INSERT INTO materials(name, componenttype, vatrate, project_id, unitcost, quantity, transportcost, qualitycoefficient) VALUES(?,'Material',?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, material.getName());
            preparedStatement.setDouble(2, material.getVatRate());
            preparedStatement.setInt(3, material.getProject().getId());
            preparedStatement.setDouble(4, material.getUnitCost());
            preparedStatement.setDouble(5, material.getQuantity());
            preparedStatement.setDouble(6, material.getTransportCost());
            preparedStatement.setDouble(7, material.getCoefficientQuality());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                material.setId(generatedKeys.getInt(1));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return material;
    }

    @Override
    public Optional<Material> findById(int id) {
        String query = "SELECT * FROM materials WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Material material = new Material();
                material.setName(resultSet.getString("name"));
                material.setVatRate(resultSet.getDouble("vatrate"));;
                material.setUnitCost(resultSet.getDouble("unitcost"));
                material.setQuantity(resultSet.getDouble("quantity"));
                material.setTransportCost(resultSet.getDouble("transportcost"));
                material.setCoefficientQuality(resultSet.getDouble("qualitycoefficient"));
                return Optional.of(material);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Material> findAll() {
        String query = "SELECT * FROM materials";
        List<Material> materials = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Material material = new Material();
                material.setName(resultSet.getString("name"));
                material.setVatRate(resultSet.getDouble("vatrate"));;
                material.setUnitCost(resultSet.getDouble("unitcost"));
                material.setQuantity(resultSet.getDouble("quantity"));
                material.setTransportCost(resultSet.getDouble("transportcost"));
                material.setCoefficientQuality(resultSet.getDouble("qualitycoefficient"));
                materials.add(material);
                return materials;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Material update(Material material) {
        String query = "UPDATE materials SET name = ?, vatrate = ?, project_id = ?, unitcost = ?, quantity = ?, transportcost = ?, qualitycoefficient = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, material.getName());
            statement.setDouble(2, material.getVatRate());
            statement.setInt(3, material.getProject().getId());
            statement.setDouble(4, material.getUnitCost());
            statement.setDouble(5, material.getQuantity());
            statement.setDouble(6, material.getTransportCost());
            statement.setDouble(7, material.getCoefficientQuality());
            statement.setInt(8, material.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return material;
    }

    @Override
    public Boolean delete(int id) {
        String query = "DELETE FROM materials WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public List<Material> findByProject(Project project) {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, project.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Material material = new Material();
                material.setId(resultSet.getInt("id"));
                material.setName(resultSet.getString("name"));
                material.setVatRate(resultSet.getDouble("vatrate"));
                material.setUnitCost(resultSet.getDouble("unitcost"));
                material.setQuantity(resultSet.getDouble("quantity"));
                material.setTransportCost(resultSet.getDouble("transportcost"));
                material.setCoefficientQuality(resultSet.getDouble("qualitycoefficient"));
                materials.add(material);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return materials;
    }
}
