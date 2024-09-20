package BatiCuisine.Services;

import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Implementation.ProjectRepository;

public class ProjectService {
    private ProjectRepository projectRepository ;

    public ProjectService() {
        this.projectRepository = projectRepository;
    }
    public Project save(Project project){
        return project;
    }
}
