package BatiCuisine.Views;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Material;
import BatiCuisine.Entities.Project;
import BatiCuisine.Enums.ProjectStatus;
import BatiCuisine.Services.ProjectService;

import java.util.Scanner;

public class ProjectView {
    private ProjectService projectService;
    private LaborView laborView ;
    private MaterialView materialView;
    Scanner scanner = new Scanner(System.in);

    public ProjectView() {

        this.projectService = new ProjectService();
        this.laborView = new LaborView();
        this.materialView = new MaterialView();
    }
    public void saveProjectForClient(Client client){
        System.out.println("-----Création d'un Nouveau Proje----");
        System.out.print("Entrer nom de projet");
        String name =scanner.nextLine();
        Project project = new Project(name, 0.0,0.0, ProjectStatus.INPROGRESS,client);
        Project savedProject = projectService.save(project);
        materialView.createMaterial(savedProject);
        laborView.creatLabor(savedProject);



    }
}
