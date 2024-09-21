package BatiCuisine.Repository.Implementation;

import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Entities.Material;
import BatiCuisine.Repository.Interface.MaterialInterface;

import java.sql.*;
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
            preparedStatement.setDouble(6, material.setCoefficientQuality());
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
        return Optional.empty();
    }

    @Override
    public List<Material> findAll() {
        return null;
    }

    @Override
    public Material update(Material material) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }
}
