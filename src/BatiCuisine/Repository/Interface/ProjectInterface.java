package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Project;

public interface ProjectInterface {
  Project save(Project project);
  Project update(Project project);

}
