package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import java.util.List;
import java.util.Optional;

public interface ClientInterface {

    Client save(Client client) ;

    Optional<Client> findById(int id);

    List<Client> findAll() ;

    Client update(Client client);

    Boolean delete(int id);

    Optional<Client> findByName(String name) ;
}
