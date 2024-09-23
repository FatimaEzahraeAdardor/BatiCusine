package BatiCuisine.Views;

import BatiCuisine.Entities.Labor;
import BatiCuisine.Entities.Project;
import BatiCuisine.Services.LaborService;

import java.util.Scanner;

public class LaborView {
    LaborService laborService = new LaborService();
    Scanner scanner =new Scanner(System.in);
    public void creatLabor(Project project){
        do {
            System.out.println("---Ajout de la main-d'œuvre---");
            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste):");
            String name = scanner.nextLine();
            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) ");
            Double hoursCost = scanner.nextDouble();
            System.out.print("Entrez le nombre d'heures travaillées:");
            Double workingHours = scanner.nextDouble();
            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité):");
            Double workerProductivity = scanner.nextDouble();
            Labor labor = new Labor(name,"Labor",0.20, project, hoursCost, workingHours, workerProductivity);
            laborService.save(labor);
            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n):");
        }while (scanner.nextLine().equalsIgnoreCase("y"));

    }

}
