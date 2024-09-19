package BatiCuisine.Services;

import BatiCuisine.Entities.Client;
import BatiCuisine.Repository.Implementation.ClientRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientService {
  ClientRepository clientRepository ;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public Client save(Client client){
        Optional<Client> existingClient = clientRepository.findByName(client.getName());
        if(existingClient.isPresent()){
            return null;
        }
      return clientRepository.save(client);
  }
  public Optional<Client> findById(int id){
      return clientRepository.findById(id);
  }
  public Optional<Client> findByName(String name){
        return clientRepository.findByName(name);
  }
  public List<Client> findAll(){
      return clientRepository.findAll();
  }
  public Client update(Client client){
    return clientRepository.update(client);
  }
  public Boolean delete(int id) {
      return clientRepository.delete(id);
  }
}
