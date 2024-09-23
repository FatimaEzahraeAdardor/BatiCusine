package BatiCuisine.Views;

import BatiCuisine.Entities.*;
import BatiCuisine.Enums.ProjectStatus;
import BatiCuisine.Services.LaborService;
import BatiCuisine.Services.MaterialService;
import BatiCuisine.Services.ProjectService;

import java.util.List;
import java.util.Scanner;

public class ProjectView {
    private ProjectService projectService;
    private LaborView laborView;
    private MaterialView materialView;
    private MaterialService materialService;
    private LaborService laborService;
    private QuoteView quoteView = new QuoteView();
    private Scanner scanner = new Scanner(System.in);

    public ProjectView() {
        this.projectService = new ProjectService();
        this.laborView = new LaborView();
        this.materialView = new MaterialView();
        this.materialService = new MaterialService();
        this.laborService = new LaborService();

    }

    public void saveProjectForClient(Client client) {
        System.out.println("-----Création d'un Nouveau Projet-----");
        System.out.print("Entrer nom de projet : ");
        String name = scanner.nextLine();
        Project project = new Project(name, 0.0, 0.0, ProjectStatus.INPROGRESS, client);
        Project savedProject = projectService.save(project);
        materialView.createMaterial(savedProject);
        laborView.creatLabor(savedProject);
        calculateProjectCost(savedProject);
        quoteView.AddQuote(savedProject);

    }

    public void calculateProjectCost(Project project) {
        double totalMaterialCost = 0;
        double totalLaborCost = 0;
        System.out.println("--- Calcul du coût total ---");

        // Calcul du coût total des matériaux
        List<Material> materials = materialService.findByProject(project);
        for (Material material : materials) {
            double materialCost = material.calculateTotalCost();
            totalMaterialCost += materialCost;
        }

        // Calcul du coût total de la main-d'œuvre
        List<Labor> labors = laborService.findByProject(project);
        for (Labor labor : labors) {
            double laborCost = labor.calculateTotalCost();
            totalLaborCost += laborCost;
        }
        double totalCostBeforeVAT = totalMaterialCost + totalLaborCost;

        // Application de la TVA
        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        boolean applyVAT = scanner.nextLine().equalsIgnoreCase("y");
        double vatRate = 0;
        if (applyVAT) {
            vatRate = promptForDouble("Entrez le pourcentage de TVA (%) : ") ;
        }
        double totalCostWithVAT = totalCostBeforeVAT * (1 + vatRate/100);

        // Application de la marge bénéficiaire
        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        boolean applyProfitMargin = scanner.nextLine().equalsIgnoreCase("y");
        double profitMarginRate = 0;
        if (applyProfitMargin) {
            profitMarginRate = promptForDouble("Entrez le pourcentage de marge bénéficiaire (%) : ") ;
        }
        double finalTotalCost = totalCostWithVAT * (1 + profitMarginRate/100);

        // Mise à jour du projet avec les coûts calculés
        project.setTotalCost(finalTotalCost);
        project.setProfitMargin(profitMarginRate);
        projectService.update(project);
        // Affichage des résultats
        displayProjectDetails(project, totalMaterialCost, totalLaborCost, totalCostBeforeVAT, vatRate, profitMarginRate, finalTotalCost);
    }
    public void displayProjectDetails(Project project, double materialCost, double laborCost, double costBeforeVAT, double vatRate, double profitMarginRate, double finalCost) {
        System.out.println("--- Résultat du Calcul ---");
        System.out.printf("Nom du projet : \n" + project.getProjectName());
        System.out.printf("Client : \n" + project.getClient().getName());
        System.out.printf("Adresse du chantier : \n" + project.getClient().getAddress());

        System.out.println("--- Détail des Coûts ---");
        System.out.printf("1. Matériaux :\n- Coût total  : %.2f €\n", materialCost);
        System.out.printf("2. Main-d'œuvre :\n- Coût total  : %.2f €\n", laborCost);
        System.out.printf("3. Coût total avant Marge bénéficiaire : %.2f €\n", costBeforeVAT);
        System.out.printf("4. Coût total de projet avant TVA : %.2f €\n", costBeforeVAT);

        if (vatRate > 0) {
            System.out.printf("4. TVA (%.2f%%) : %.2f €\n", vatRate, costBeforeVAT * vatRate/100);
        }
        System.out.printf("5. Coût total avec TVA : %.2f €\n", costBeforeVAT * (1 + vatRate/100));
        if (profitMarginRate > 0) {
            System.out.printf("6. Marge bénéficiaire (%.2f%%) : %.2f €\n", profitMarginRate, costBeforeVAT * (1 + vatRate/100) * profitMarginRate/100);
        }
        System.out.printf("7. Coût total final du projet : %.2f €\n", finalCost);
    }

    public void displayAllProjects() {
        System.out.println("--- Liste de tous les projets ---");
        List<Project> projects = projectService.findAll();
        if (projects.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
        } else {
            System.out.println("\n+------+------------------+--------------------------+---------------------+------------------+------------------+");
            System.out.printf("| %-4s | %-16s | %-24s | %-19s | %-16s | %-16s |\n",
                    "ID", "Nom de Projet", "Marge bénéficiaire", "Coût total", "Status de Projet", "Nom de Client");
            System.out.println("+------+------------------+--------------------------+---------------------+------------------+------------------+");

            for (Project project : projects) {
                System.out.printf("| %-4s | %-16s | %-24s | %-19s | %-16s | %-16s |\n",
                        project.getId(),
                        project.getProjectName(),
                        project.getProfitMargin(),
                        project.getTotalCost(),
                        project.getStatus(),
                        project.getClient().getName());
                System.out.println("+------+------------------+--------------------------+---------------------+------------------+------------------+");
            }
        }

}
    // Méthode utilitaire pour demander un double à l'utilisateur
    private double promptForDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }
}
