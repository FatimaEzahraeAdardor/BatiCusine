package BatiCuisine.Views;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Project;
import BatiCuisine.Enums.ProjectStatus;
import BatiCuisine.Services.ProjectService;

import java.util.Scanner;

public class ProjectView {
    private ProjectService projectService;
    Scanner scanner = new Scanner(System.in);

    public ProjectView() {
        this.projectService = projectService;
    }
    public void saveProjectForClient(Client client){
        System.out.println("-----Création d'un Nouveau Proje----");
        System.out.println("Entrer nom de projet");
        String name =scanner.nextLine();
        Project project = new Project(name, 0.0,0.0, ProjectStatus.INPROGRESS,client);
        Project saveProject = projectService.save(project);

    }
}
