package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Labor;

import java.util.List;
import java.util.Optional;

public interface LaborInterface {
    Labor save(Labor labor);
    Optional<Labor> findById(int id);

    List<Labor> findAll() ;

    Labor update(Labor labor);

    Boolean delete(int id);

}
