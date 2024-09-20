package BatiCuisine.Repository.Implementation;

import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Labor;
import BatiCuisine.Repository.Interface.LaborInterface;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class LaborRepository implements LaborInterface {
    private Connection connection;

    public LaborRepository(){
        this.connection = DataBaseConnection.getInstance().getConnection();
    }


    @Override
    public Labor save(Labor labor) {
        String query = "INSERT INTO labor(name, componenttype, vatrate, project_id, hourlyrate, hoursworked, workerproductivity) VALUES(?,'Labor',?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, labor.getName());
            preparedStatement.setDouble(2, labor.getVatRate());
            preparedStatement.setInt(3, labor.getProject().getId());
            preparedStatement.setDouble(4, labor.getHourlyCost());
            preparedStatement.setDouble(5, labor.getWorkingHours());
            preparedStatement.setDouble(6, labor.getWorkerProductivity());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                labor.setId(generatedKeys.getInt(1));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return labor;
    }

    @Override
    public Optional<Labor> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Labor> findAll() {
        return null;
    }

    @Override
    public Client update(Labor labor) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }
}
