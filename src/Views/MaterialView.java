package Views;

import Entities.Material;
import Entities.Project;
import Repository.Implementation.MaterialRepository;
import utils.InputValidator;

import java.util.Scanner;

public class MaterialView {
    private MaterialRepository materialRepository;
    Scanner scanner = new Scanner(System.in);
    public MaterialView() {

        this.materialRepository = new MaterialRepository();
    }
    public void createMaterial(Project project) {
        System.out.println("----------------------- Ajout des materiaux -------------------------");

        do {
            String name = InputValidator.promptForString("Entrez le nom du materiau : ");
            double quantity = InputValidator.promptForDouble("Entrez la quantite de ce materiau (en m²) : ");
            double unitCost = InputValidator.promptForDouble("Entrez le cout unitaire de ce materiau (€/m²) : ");
            double transportCost = InputValidator.promptForDouble("Entrez le cout de transport de ce materiau (€) : ");
            double qualityCoefficient = InputValidator.promptForDouble("Entrez le coefficient de qualite du materiau (1.0 = standard, > 1.0 = haute qualite) : ");

            Material material = new Material(name, "Material", 0.20, project, unitCost, quantity, transportCost, qualityCoefficient);
            materialRepository.save(material);

            System.out.println("Materiau ajoute avec succès !");
            System.out.print("Voulez-vous ajouter un autre materiau ? (y/n) : ");

        } while (scanner.nextLine().equalsIgnoreCase("y"));
    }


}
