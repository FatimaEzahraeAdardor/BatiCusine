package BatiCuisine.Repository.Implementation;

import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Project;
import BatiCuisine.Enums.ProjectStatus;
import BatiCuisine.Repository.Interface.ProjectInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements ProjectInterface {
    private Connection connection;
    private ClientRepository clientRepository;

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
    @Override
    public Project update(Project project) {
        String query = "UPDATE projects SET projectname = ?, profitmargin = ?, totalcost = ?, projectstatus = ?::project_status, client_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, project.getProjectName());
            statement.setDouble(2, project.getProfitMargin());
            statement.setDouble(3, project.getTotalCost());
            statement.setObject(4, project.getStatus().name());
            statement.setInt(5, project.getClient().getId());
            statement.setInt(6, project.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return project;
    }

    @Override
    public Optional<Project> findById(int id) {
        return Optional.empty();
    }
    @Override
    public Project findByName(String name){
        String query = "SELECT * FROM Projects WHERE projectname = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Project project = new Project();
                    project.setId(rs.getInt("id"));
                    project.setProjectName(rs.getString("projectname"));
                    project.setProfitMargin(rs.getDouble("profitmargin"));
                    project.setTotalCost(rs.getDouble("totalcost"));
                    project.setStatus(ProjectStatus.valueOf(rs.getString("projectstatus")));
                    project.setClient(clientRepository.findById(rs.getInt("client_id")).get());
                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Project> findAll() {
        String query = "SELECT p.*, c.id AS client_id, c.name AS client_name FROM projects p JOIN clients c ON p.client_id = c.id";
        List<Project> projects = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setProjectName(rs.getString("projectname"));
                project.setProfitMargin(rs.getDouble("profitmargin"));
                project.setTotalCost(rs.getDouble("totalcost"));
                project.setStatus(ProjectStatus.valueOf(rs.getString("projectstatus")));
                Client client = new Client();
                client.setId(rs.getInt("client_id"));
                client.setName(rs.getString("client_name"));

                project.setClient(client);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return projects;
    }

}
