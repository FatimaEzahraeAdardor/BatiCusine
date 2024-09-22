package BatiCuisine.Services;

import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Implementation.ProjectRepository;

import java.util.Optional;

public class ProjectService {
    private ProjectRepository projectRepository ;

    public ProjectService() {

        this.projectRepository = new ProjectRepository();
    }
    public Project save(Project project){
        return projectRepository.save(project);
    }
    public void update(Project project) {
        projectRepository.update(project);
    }
    public Project findByName(String name){
        return projectRepository.findByName(name);
    }
}
