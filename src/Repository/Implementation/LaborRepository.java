package Repository.Implementation;

import Config.DataBaseConnection;
import Entities.Client;
import Entities.Labor;
import Entities.Project;
import Repository.Interface.LaborInterface;

import java.sql.*;
import java.util.ArrayList;
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
        String query = "SELECT * FROM labor WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Labor labor = new Labor();
                 labor.setName(resultSet.getString("name"));
                 labor.setVatRate(resultSet.getDouble("vatrate"));;
                labor.setHourlyCost(resultSet.getDouble("hourlycost"));
                labor.setWorkingHours(resultSet.getDouble("workinghours"));
                labor.setWorkerProductivity(resultSet.getDouble("workerproductivity"));
                return Optional.of(labor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Labor> findAll() {
        String query = "SELECT * FROM labor";
        List<Labor> labors = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Labor labor = new Labor();
                labor.setName(resultSet.getString("name"));
                labor.setVatRate(resultSet.getDouble("vatrate"));;
                labor.setHourlyCost(resultSet.getDouble("hourlycost"));
                labor.setWorkingHours(resultSet.getDouble("workinghours"));
                labor.setWorkerProductivity(resultSet.getDouble("workerproductivity"));
                labors.add(labor);
                return labors;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Labor update(Labor labor) {
        String query = "UPDATE labor SET name = ?, vatrate = ?, project_id = ?, hourlyrate = ?, hoursworked = ?, workerproductivity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, labor.getName());
            statement.setDouble(2, labor.getVatRate());
            statement.setInt(3, labor.getProject().getId());
            statement.setDouble(4, labor.getHourlyCost());
            statement.setDouble(5, labor.getWorkingHours());
            statement.setDouble(6, labor.getWorkerProductivity());
            statement.setInt(7, labor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return labor;

    }

    @Override
    public Boolean delete(int id) {
        String query = "DELETE FROM labor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public List<Labor> findByProject(Project project) {
        List<Labor> labors = new ArrayList<>();
        String query = "SELECT * FROM labor WHERE project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, project.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Labor labor = new Labor();
                labor.setId(resultSet.getInt("id"));
                labor.setName(resultSet.getString("name"));
                labor.setVatRate(resultSet.getDouble("vatrate"));
                labor.setHourlyCost(resultSet.getDouble("hourlyrate"));
                labor.setWorkingHours(resultSet.getDouble("hoursworked"));
                labor.setWorkerProductivity(resultSet.getDouble("workerproductivity"));
                labors.add(labor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return labors;
    }
}
