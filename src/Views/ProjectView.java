package Views;

import Entities.*;
import Enums.ProjectStatus;
import Services.LaborService;
import Services.MaterialService;
import Services.ProjectService;
import utils.InputValidator;

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
        System.out.println("-------------------- Creation d'un Nouveau Projet ------------------");
        String name = InputValidator.promptForString("Entrer nom de projet : ");
        Project project = new Project(name, 0.0, 0.0, ProjectStatus.INPROGRESS, client);
        Project savedProject = projectService.save(project);
        materialView.createMaterial(savedProject);
        laborView.createLabor(savedProject);
        calculateProjectCost(savedProject);
        quoteView.AddQuote(savedProject);

    }

    public void calculateProjectCost(Project project) {
        double totalMaterialCost = 0;
        double totalLaborCost = 0;
        System.out.println("-------------------- Calcul du cout total --------------------------");

        // Calculer le cout total des materiaux et de la main-d'œuvre
        totalMaterialCost = materialService.calculateTotalMaterialCost(project);
        totalLaborCost = laborService.calculateTotalLaborCost(project);

        double totalCostBeforeVAT = totalMaterialCost + totalLaborCost;

        // Appliquer la TVA si necessaire
        boolean applyVAT = InputValidator.promptForString("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ")
                .equalsIgnoreCase("y");
        double vatRate = 0;
        if (applyVAT) {
            vatRate = InputValidator.promptForDouble("Entrez le pourcentage de TVA (%) : ");
        }
        double totalCostWithVAT = totalCostBeforeVAT * (1 + vatRate / 100);

        // Appliquer la marge beneficiaire si necessaire
        boolean applyProfitMargin = InputValidator.promptForString("Souhaitez-vous appliquer une marge beneficiaire au projet ? (y/n) : ")
                .equalsIgnoreCase("y");
        double profitMarginRate = 0;
        if (applyProfitMargin) {
            profitMarginRate = InputValidator.promptForDouble("Entrez le pourcentage de marge beneficiaire (%) : ");
        }
        double finalTotalCost = totalCostWithVAT * (1 + profitMarginRate / 100);

        // Demander si le client est professionnel pour appliquer une remise
        boolean isProfessionalClient = project.getClient().isProfessional();
        if (isProfessionalClient) {
            boolean applyDiscount = InputValidator.promptForString("Souhaitez-vous appliquer une remise au client professionnel ? (y/n) : ")
                    .equalsIgnoreCase("y");
            if (applyDiscount) {
                double discountRate = InputValidator.promptForDouble("Entrez le pourcentage de remise (%) : ");
                finalTotalCost = finalTotalCost * (1 - discountRate / 100);
            }
        }

        // Mettre  jour le projet avec les couts calcules
        project.setTotalCost(finalTotalCost);
        project.setProfitMargin(profitMarginRate);
        projectService.update(project);

        displayProjectDetails(project, totalMaterialCost, totalLaborCost, totalCostBeforeVAT, vatRate, profitMarginRate, finalTotalCost);
    }

    public void displayProjectDetails(Project project, double materialCost, double laborCost, double costBeforeVAT, double vatRate, double profitMarginRate, double finalCost) {
        System.out.println("-------------------- Resultat du Calcul ----------------------------");
        System.out.println("Nom du projet : \n" + project.getProjectName());
        System.out.println("Client : \n" + project.getClient().getName());
        System.out.println("Adresse du chantier : \n" + project.getClient().getAddress());

        System.out.println("--- Detail des Couts ---");
        System.out.printf("1. Materiaux :\n- Cout total  : %.2f €\n", materialCost);
        System.out.printf("2. Main-d'œuvre :\n- Cout total  : %.2f €\n", laborCost);
        System.out.printf("3. Cout total avant Marge beneficiaire : %.2f €\n", costBeforeVAT);
        System.out.printf("4. Cout total de projet avant TVA : %.2f €\n", costBeforeVAT);

        if (vatRate > 0) {
            System.out.printf("4. TVA (%.2f%%) : %.2f €\n", vatRate, costBeforeVAT * vatRate/100);
        }
        System.out.printf("5. Cout total avec TVA : %.2f €\n", costBeforeVAT * (1 + vatRate/100));
        if (profitMarginRate > 0) {
            System.out.printf("6. Marge beneficiaire (%.2f%%) : %.2f €\n", profitMarginRate, costBeforeVAT * (1 + vatRate/100) * profitMarginRate/100);
        }
        System.out.printf("7. Cout total final du projet : %.2f €\n", finalCost);
    }

    public void displayAllProjects() {
        System.out.println("-------------------- Liste de tous les projets ---------------------");
        List<Project> projects = projectService.findAll();
        if (projects.isEmpty()) {
            System.out.println("Aucun projet trouve.");
        } else {
            System.out.println("\n+------+------------------+--------------------------+---------------------+------------------+------------------+");
            System.out.printf("| %-4s | %-16s | %-24s | %-19s | %-16s | %-16s |\n",
                    "ID", "Nom de Projet", "Marge beneficiaire", "Cout total", "Status de Projet", "Nom de Client");
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

}
