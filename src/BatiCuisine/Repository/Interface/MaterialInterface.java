package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Labor;
import BatiCuisine.Entities.Material;

import java.util.List;
import java.util.Optional;

public interface MaterialInterface {
    Material save(Material material);
    Optional<Material> findById(int id);

    List<Material> findAll() ;

    Material update(Material material);

    Boolean delete(int id);
}
