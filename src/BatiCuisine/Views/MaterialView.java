package BatiCuisine.Views;

import BatiCuisine.Entities.Material;
import BatiCuisine.Entities.Project;
import BatiCuisine.Repository.Implementation.MaterialRepository;

import java.util.Scanner;

public class MaterialView {
    private MaterialRepository materialRepository;
    Scanner scanner = new Scanner(System.in);
    public MaterialView() {

        this.materialRepository = new MaterialRepository();
    }
    public void createMaterial(Project project){
        System.out.println("--- Ajout des matériaux ---");
        do {
            System.out.print("Entrez le nom du matériau : ");
            String name = scanner.nextLine();
            System.out.print("Entrez la quantité de ce matériau (en m²) : ");
            Double quantity = scanner.nextDouble();
            System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
            Double unitCost = scanner.nextDouble();
            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            Double transportCost = scanner.nextDouble();
            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            Double qualityCoefficient = scanner.nextDouble();
            scanner.nextLine();
            Material material = new Material(name, "Material",0.20, project, unitCost, quantity, transportCost, qualityCoefficient);
            materialRepository.save(material);
            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) ");

        }while (scanner.nextLine().equalsIgnoreCase("y"));

    }
}
