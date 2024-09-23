package BatiCuisine.Views;

import BatiCuisine.Entities.Client;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private boolean bool;
    ClientView clientView = new ClientView();
    ProjectView projectView = new ProjectView();
    QuoteView quoteView = new QuoteView();
    public void menuPrincipal(){

        while (true){
            bool = false;
            System.out.println("* ================== Menu Principal ====================== *");
            System.out.println("*  1. Gestion de Client *");
            System.out.println("*  2. Gestion de Projet *");
            System.out.println("*  3. Gestion des devis*");
            System.out.println("*  4. Quitter *");
            System.out.println("* =========================================================== *\n");

            System.out.print("eChoisissez une option : ");
            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    clientMenu();
                    break;
                case "2":
                    projectMenu();
                    break;
                case "3":
                    quoteMenu();
                    break;
                case "4":
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }
    public void clientMenu() {
        while(!bool) {
            System.out.println("*======================= Gestion de Client ========================== *");
            System.out.println("*  1. Ajouter un client *");
            System.out.println("*  2. Modifier un client *");
            System.out.println("*  3. supprimer client *");
            System.out.println("*  4. Affichage des clients *");
            System.out.println("*  5. Return to Main Menu *");
            System.out.println("*  6. Quitter *");
            System.out.println("* ======================================================================= *\n");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    clientView.create();
                    break;
                case "2":
                    clientView.update();
                    break;
                case "3":
                    clientView.delete();
                    break;
                case "4":
                    clientView.displayAllClient();
                    break;
                case "5":
                    bool = true;
                    System.out.println("return to principal menu");
                    break;
                case "6":
                    System.out.println("Quitter");
                    System.exit(0);
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }

        }
    }
    public void projectMenu(){
        while(!bool){
            System.out.println("\n* Souhaitez-vous chercher un client existant ou en ajouter un nouveau ? *");
            System.out.println("*  1. Chercher un client existant *");
            System.out.println("*  2. Ajouter un nouveau client *");
            System.out.println("*  3. Return to Main Menu *");
            System.out.println("*  4. Quitter *");
            System.out.println("=======================================================================\n");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            Client client = null;

            switch(choice){
                case "1":
//                    client = clientUI.searchClientUI();
                    break;
                case "2":
                    client = clientView.create();
                    break;
                case "3":
                    bool = true;
                    System.out.println("return to principal menu");
                    break;
                case "4":
                    System.out.println("exit");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
            if (client != null) {
                projectView.saveProjectForClient(client);
            } else {
                System.out.println("Client non trouvé ou création échouée.");
            }

        }
    }
    public void quoteMenu() {
        boolean quitter = false;
        while(!quitter) {
            System.out.println("*====================== Gestion des devis ===============================*");
            System.out.println("*  1. Afficher tous les projets *");
            System.out.println("*  2. Afficher tous les devis *");
            System.out.println("*  3. Accepter un devis *");
            System.out.println("*  4. Supprimer un devis *");
            System.out.println("*  5. Retourner au menu principal *");
            System.out.println("*  6. Quitter *");

            System.out.print("Entrez votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    projectView.displayAllProjects();
                    break;
                case "2":
                    quoteView.displayAllQuotes();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    quitter = true;
                    System.out.println("Retour au menu principal.");
                    break;
                case "6":
                    System.out.println("Quitter.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

}
