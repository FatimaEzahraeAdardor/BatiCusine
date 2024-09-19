package BatiCuisine.Services;

import BatiCuisine.Entities.Client;
import BatiCuisine.Repository.Implementation.ClientRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientService {
  ClientRepository clientRepository = new ClientRepository();

  public Client save(Client client) throws SQLException {
      return clientRepository.save(client);
  }
  public Optional<Client> findById(int id) throws SQLException {
      return clientRepository.findById(id);
  }
  public List<Client> findAll() throws SQLException {
      return clientRepository.findAll();
  }
  public Optional<Client> update(Client client) throws SQLException {
      Optional<Client> existingClient = findById(client.getId());
      if (existingClient.isPresent()){
          return Optional.of(client);
      }
      return Optional.empty();
  }
  public Boolean delete(int id) throws SQLException {
      Optional<Client> existingClient = findById(id);
      if (existingClient.isPresent()){
         clientRepository.delete(id);
      }
      return true;
  }
}
