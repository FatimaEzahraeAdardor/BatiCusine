package Views;

import Entities.Labor;
import Entities.Project;
import Services.LaborService;
import utils.InputValidator;

import java.util.Scanner;

public class LaborView {
    LaborService laborService = new LaborService();
    Scanner scanner =new Scanner(System.in);
    public void createLabor(Project project) {
        System.out.println("-------------------- Ajout de la main-d'œuvre ----------------------");
        do {
            String name = InputValidator.promptForString("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Specialiste) : ");
            double hoursCost = InputValidator.promptForDouble("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double workingHours = InputValidator.promptForDouble("Entrez le nombre d'heures travaillees : ");
            double workerProductivity = InputValidator.promptForDouble("Entrez le facteur de productivite (1.0 = standard, > 1.0 = haute productivite) : ");

            Labor labor = new Labor(name, "Labor", 0.20, project, hoursCost, workingHours, workerProductivity);
            laborService.save(labor);

            System.out.println("Main-d'œuvre ajoutee avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
        } while (scanner.nextLine().equalsIgnoreCase("y"));
    }


}
