package BatiCuisine.Views;

import BatiCuisine.Entities.Material;
import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Implementation.MaterialRepository;
import BatiCuisine.utils.InputValidator;

import java.util.Scanner;

public class MaterialView {
    private MaterialRepository materialRepository;
    Scanner scanner = new Scanner(System.in);
    public MaterialView() {

        this.materialRepository = new MaterialRepository();
    }
    public void createMaterial(Project project) {
        System.out.println("--- Ajout des matériaux ---");

        do {
            String name = InputValidator.promptForString("Entrez le nom du matériau : ");
            double quantity = InputValidator.promptForDouble("Entrez la quantité de ce matériau (en m²) : ");
            double unitCost = InputValidator.promptForDouble("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double transportCost = InputValidator.promptForDouble("Entrez le coût de transport de ce matériau (€) : ");
            double qualityCoefficient = InputValidator.promptForDouble("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");

            Material material = new Material(name, "Material", 0.20, project, unitCost, quantity, transportCost, qualityCoefficient);
            materialRepository.save(material);

            System.out.println("Matériau ajouté avec succès !");
            scanner.nextLine();

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");

        } while (scanner.nextLine().equalsIgnoreCase("y"));
    }


}
