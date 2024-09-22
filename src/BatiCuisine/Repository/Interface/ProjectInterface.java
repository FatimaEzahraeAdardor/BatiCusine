package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Project;

import java.util.Optional;

public interface ProjectInterface {
  Project save(Project project);
  Project update(Project project);
  Optional<Project> findById(int id);
  Project findByName(String name);


}
