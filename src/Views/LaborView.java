package BatiCuisine.Views;

import BatiCuisine.Entities.Labor;
import BatiCuisine.Entities.Project;
import BatiCuisine.Services.LaborService;
import BatiCuisine.utils.InputValidator;

import java.util.Scanner;

public class LaborView {
    LaborService laborService = new LaborService();
    Scanner scanner =new Scanner(System.in);
    public void createLabor(Project project) {
        System.out.println("-------------------- Ajout de la main-d'œuvre ----------------------");
        do {
            String name = InputValidator.promptForString("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            double hoursCost = InputValidator.promptForDouble("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double workingHours = InputValidator.promptForDouble("Entrez le nombre d'heures travaillées : ");
            double workerProductivity = InputValidator.promptForDouble("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");

            Labor labor = new Labor(name, "Labor", 0.20, project, hoursCost, workingHours, workerProductivity);
            laborService.save(labor);

            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
        } while (scanner.nextLine().equalsIgnoreCase("y"));
    }


}
