package Repository.Implementation;

import Config.DataBaseConnection;
import Entities.Project;
import Entities.Quote;
import Repository.Interface.QuoteInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuoteRepository implements QuoteInterface {
    private Connection connection;
    private ProjectRepository projectRepository;

    public QuoteRepository() {
        this.connection = DataBaseConnection.getInstance().getConnection();
        this.projectRepository  = new ProjectRepository();
    }

    @Override
    public Quote save(Quote quote) {
        String query = "INSERT INTO quotes (estimatedamount, issuedate, validitydate, isaccepted, project_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, quote.getEstimatedAmount());
            preparedStatement.setDate(2, Date.valueOf(quote.getIssueDate()));
            preparedStatement.setDate(3, Date.valueOf(quote.getValidatedDate()));
            preparedStatement.setBoolean(4, quote.isAccepted());
            preparedStatement.setInt(5, quote.getProject().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                quote.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quote;

    }

  @Override
//    public Optional<Quote> findById(int id) {
//         String query = "SELECT * FROM quotes WHERE id = ?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                Quote quote = new Quote();
//                quote.setId(rs.getInt("id"));
//                quote.setEstimatedAmount(rs.getDouble("estimatedamount"));
//                quote.setIssueDate(rs.getDate("issuedate").toLocalDate());
//                quote.setValidatedDate(rs.getDate("validitydate").toLocalDate());
//                quote.setAccepted(rs.getBoolean("isaccepted"));
//
//                return Optional.of(quote);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return Optional.empty();
//    }
  public Optional<Quote> findById(int id) {
      String query = "SELECT q.id, q.estimatedamount, q.issuedate, q.validitydate, q.isaccepted, p.id AS project_id, p.projectname " +
              "FROM quotes q " +
              "LEFT JOIN projects p ON q.project_id = p.id " +
              "WHERE q.id = ?";
      try (PreparedStatement statement = connection.prepareStatement(query)) {
          statement.setInt(1, id);
          ResultSet resultSet = statement.executeQuery();
          if (resultSet.next()) {
              Quote quote = new Quote();
              quote.setId(resultSet.getInt("id"));
              quote.setEstimatedAmount(resultSet.getDouble("estimatedamount"));
              quote.setIssueDate(resultSet.getDate("issuedate").toLocalDate());
              quote.setValidatedDate(resultSet.getDate("validitydate").toLocalDate());
              quote.setAccepted(resultSet.getBoolean("isaccepted"));

              // Recuperer le projet associe si present
              int projectId = resultSet.getInt("project_id");
              if (!resultSet.wasNull()) {
                  Project project = new Project();
                  project.setId(projectId);
                  project.setProjectName(resultSet.getString("projectname"));
                  // Vous pouvez egalement recuperer d'autres attributs du projet ici si necessaire
                  quote.setProject(project);
              }

              return Optional.of(quote);
          }
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
      return Optional.empty();
  }


    @Override
    public Optional<Quote> findByProjectId(int id) {
        String query = "SELECT q.*, p.id AS project_id, p.projectname AS project_name FROM quotes q JOIN projects p ON p.id = q.project_id WHERE q.project_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Quote quote = new Quote();
                quote.setId(rs.getInt("id"));
                quote.setEstimatedAmount(rs.getDouble("estimatedamount"));
                quote.setIssueDate(rs.getDate("issuedate").toLocalDate());
                quote.setValidatedDate(rs.getDate("validitydate").toLocalDate());
                quote.setAccepted(rs.getBoolean("isaccepted"));

                Project project = new Project();
                project.setId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                quote.setProject(project);
                return Optional.of(quote);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public List<Quote> findAll() {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT q.*, p.id AS project_id, p.projectname AS project_name FROM quotes q JOIN projects p ON p.id = q.project_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Quote quote = new Quote();
                quote.setId(rs.getInt("id"));
                quote.setEstimatedAmount(rs.getDouble("estimatedamount"));
                quote.setIssueDate(rs.getDate("issuedate").toLocalDate());
                quote.setValidatedDate(rs.getDate("validitydate").toLocalDate());
                quote.setAccepted(rs.getBoolean("isaccepted"));

                Project project = new Project();
                project.setId(rs.getInt("project_id"));
                project.setProjectName(rs.getString("project_name"));
                quote.setProject(project);
                quotes.add(quote);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving quotes: " + e.getMessage());
        }
        return quotes;
    }

    @Override
    public Quote update(Quote quote) {
        String query = "UPDATE quotes SET isaccepted = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, quote.isAccepted());
            preparedStatement.setInt(2, quote.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return quote;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quote;
    }

    @Override
    public Boolean delete(int id) {
        String query = "DELETE FROM quotes WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
