package BatiCuisine.Services;

import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Implementation.ProjectRepository;

import java.util.List;
import java.util.Optional;

public class ProjectService {
    private ProjectRepository projectRepository ;

    public ProjectService() {

        this.projectRepository = new ProjectRepository();
    }
    public Project save(Project project){
        return projectRepository.save(project);
    }
    public Project update(Project project) {
       return projectRepository.update(project);
    }
    public Optional<Project> findById(int id){
        return projectRepository.findById(id);
    }
    public Project findByName(String name){
        return projectRepository.findByName(name);
    }
    public List<Project> findAll(){
        return projectRepository.findAll();
    }
}
