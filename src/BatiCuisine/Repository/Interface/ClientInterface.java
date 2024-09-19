package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClientInterface {

    Client save(Client client) throws SQLException;

    Optional<Client> findById(int id) throws SQLException;

    List<Client> findAll() throws SQLException;

    Optional<Client> update(Client client) throws SQLException;

    Boolean delete(int id) throws SQLException;

    Optional<Client> findByName(String name) throws SQLException;
}
