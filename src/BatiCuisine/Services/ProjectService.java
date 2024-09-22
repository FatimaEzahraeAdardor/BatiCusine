package BatiCuisine.Services;

import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Implementation.ProjectRepository;

public class ProjectService {
    private ProjectRepository projectRepository ;

    public ProjectService() {

        this.projectRepository = new ProjectRepository();
    }
    public Project save(Project project){
        return projectRepository.save(project);
    }
}
