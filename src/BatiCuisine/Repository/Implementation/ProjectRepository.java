package BatiCuisine.Repository.Implementation;

import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Interface.ProjectInterface;

import java.sql.*;

public class ProjectRepository implements ProjectInterface {
    private Connection connection;

    public ProjectRepository() {
        this.connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public Project save(Project project) {
        String query = "INSERT INTO projects(projectname, profitmargin, totalcost, projectstatus, client_id) VALUES(?,?,?,?::project_status,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setDouble(2,project.getProfitMargin());
            preparedStatement.setDouble(3,project.getTotalCost());
            preparedStatement.setObject(4,project.getStatus().name());
            preparedStatement.setInt(5,project.getClient().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                project.setId(resultSet.getInt(1));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return project;
    }
}
