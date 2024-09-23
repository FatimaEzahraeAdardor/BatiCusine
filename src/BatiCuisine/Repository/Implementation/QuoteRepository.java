package BatiCuisine.Repository.Implementation;

import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Entities.Project;
import BatiCuisine.Entities.Quote;
import BatiCuisine.Repository.Interface.QuoteInterface;

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
    public Optional<Quote> findById(int id) {
         String query = "SELECT * FROM quotes WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Quote quote = new Quote();
                quote.setId(rs.getInt("id"));
                quote.setEstimatedAmount(rs.getDouble("estimatedamount"));
                quote.setIssueDate(rs.getDate("issuedate").toLocalDate());
                quote.setValidatedDate(rs.getDate("validitydate").toLocalDate());
                quote.setAccepted(rs.getBoolean("isaccepted"));

                return Optional.of(quote);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Quote> findByProjectId(int id) {
        String query = "SELECT q.*, p.id AS project_id, p.projectname AS project_name FROM quotes q JOIN projects p ON p.id = q.project_id WHERE q.project_id = ?";
        List<Quote> quotes = new ArrayList<>();

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
                quotes.add(quote);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quotes;
    }
    @Override
    public List<Quote> findAll() {
        return null;
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
