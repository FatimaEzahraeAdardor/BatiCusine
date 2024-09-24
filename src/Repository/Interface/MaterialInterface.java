package Repository.Interface;

import Entities.Labor;
import Entities.Material;
import Entities.Project;

import java.util.List;
import java.util.Optional;

public interface MaterialInterface {
    Material save(Material material);
    Optional<Material> findById(int id);

    List<Material> findAll() ;

    Material update(Material material);

    Boolean delete(int id);
    List<Material> findByProject(Project project); // Nouvelle méthode

}
