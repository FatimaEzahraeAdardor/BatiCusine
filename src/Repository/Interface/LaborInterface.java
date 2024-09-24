package Repository.Interface;

import Entities.Client;
import Entities.Labor;
import Entities.Project;

import java.util.List;
import java.util.Optional;

public interface LaborInterface {
    Labor save(Labor labor);
    Optional<Labor> findById(int id);

    List<Labor> findAll() ;

    Labor update(Labor labor);

    Boolean delete(int id);
    List<Labor> findByProject(Project project);

}
