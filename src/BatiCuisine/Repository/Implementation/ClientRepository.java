package BatiCuisine.Repository.Implementation;
import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Entities.Client;
import BatiCuisine.Repository.Interface.ClientInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientInterface {
    private Connection connection;

    public ClientRepository() {
        this.connection = DataBaseConnection.getInstance().getConnection();
    }
    @Override
    public Client save(Client client) throws SQLException {
        String query = "INSERT INTO clients(name, address, phone, isprofessional) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setBoolean(4, client.isProfessional());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            }
            return client;
        }
    }

    @Override
    public Optional<Client> findById(int id) throws SQLException {
        String query = "SELECT * FROM clients WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                Boolean isProfessional = resultSet.getBoolean("isprofessional");
                Client client = new Client(name, address, phone, isProfessional);
                client.setId(id);
                return Optional.of(client);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Client> findAll() throws SQLException {
        String query = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setPhone(resultSet.getString("phone"));
                client.setProfessional(resultSet.getBoolean("isprofessional"));
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public Optional<Client> update(Client client) throws SQLException {
        String query = "UPDATE clients SET name = ?, address = ?, phone = ?, isprofessional = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getPhone());
            preparedStatement.setBoolean(4, client.isProfessional());
            preparedStatement.setInt(5, client.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return Optional.of(client);
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public Boolean delete(int id) throws SQLException {
        String query = "DELETE FROM clients WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Client> findByName(String name) throws SQLException {
        String query = "SELECT * FROM clients WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setPhone(resultSet.getString("phone"));
                client.setProfessional(resultSet.getBoolean("isprofessional"));
                return Optional.of(client);
            } else {
                return Optional.empty();
            }
        }
    }
}
