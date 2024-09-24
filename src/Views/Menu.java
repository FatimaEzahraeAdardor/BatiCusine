package Views;

import Entities.Client;

import java.util.Scanner;

public class Menu {
    // ANSI color codes
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String CYAN = "\u001B[36m";
    final String PURPLE = "\u001B[35m";
    Scanner scanner = new Scanner(System.in);
    private boolean bool;
    ClientView clientView = new ClientView();
    ProjectView projectView = new ProjectView();
    QuoteView quoteView = new QuoteView();
    public void menuPrincipal(){

        while (true){
            bool = false;
            System.out.println(CYAN + "* ============================ Menu Principal ============================ *\n" + RESET);
            System.out.println(YELLOW + "* 1. Gestion de Client                                                     *" );
            System.out.println(YELLOW + "* 2. Gestion de Projet                                                     *" );
            System.out.println(YELLOW + "* 3. Gestion des devis                                                     *" );
            System.out.println(YELLOW + "* 4. Quitter                                                               *\n");
            System.out.println(CYAN + "* ======================================================================== *\n" + RESET);


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
                    System.out.println("Option invalide. Veuillez reessayer.");
            }
        }
    }
    public void clientMenu() {
        while(!bool) {
            System.out.println(PURPLE + "* ========================= Gestion de Client ============================ *\n" + RESET);
            System.out.println(GREEN + "* 1. Ajouter un client                                                     *");
            System.out.println(GREEN + "* 2. Modifier un client                                                    *");
            System.out.println(GREEN + "* 3. supprimer client                                                      *");
            System.out.println(GREEN + "* 4. Affichage des clients                                                 *");
            System.out.println(GREEN + "* 5. Retour au menu principal                                              *");
            System.out.println(GREEN + "* 6. Quitter                                                               *\n");
            System.out.println(PURPLE +"* ======================================================================== *\n" + RESET);

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
                    System.out.println("Option invalide. Veuillez reessayer.");
            }

        }
    }
    public void projectMenu(){
        while(!bool){
            System.out.println("\n* Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?    *\n");
            System.out.println(BLUE + "* 1. Chercher un client existant                                           *");
            System.out.println(BLUE + "* 2. Ajouter un nouveau client                                             *");
            System.out.println(BLUE + "* 3. Retour au menu principal                                              *");
            System.out.println(BLUE + "* 4. Quitter                                                               *\n"+ RESET);
            System.out.println("* ======================================================================== *\n");

            System.out.print("enter your choice: ");
            String choice = scanner.nextLine();
            Client client = null;

            switch(choice){
                case "1":
                    client =clientView.searchClientByName() ;
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
                System.out.println("Client non trouve ou creation echouee.");
            }

        }
    }
    public void quoteMenu() {
        while(!bool) {
            System.out.println(YELLOW +"* ========================== Gestion des devis =========================== *\n"+ RESET);
            System.out.println("*  1. Afficher tous les projets                                            *");
            System.out.println("*  2. Afficher le devis specifique  un projet                             *");
            System.out.println("*  3. Afficher tous les devis                                              *");
            System.out.println("*  4. Accepter un devis                                                    *");
            System.out.println("*  5. Supprimer un devis                                                   *");
            System.out.println("*  6. Retour au menu principal                                             *");
            System.out.println("*  7. Quitter                                                              *\n");
            System.out.println(YELLOW + "* ======================================================================== *\n"+ RESET);
            System.out.print("Entrez votre choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    projectView.displayAllProjects();
                    break;
                case "2":
                    quoteView.displayQuoteByProject();
                    break;
                case "3":
                    quoteView.displayAllQuotes();
                    break;
                case "4":
                    quoteView.AccepterQuote();
                    break;
                case "5":
                    quoteView.delete();
                    break;
                case "6":
                    bool = true;
                    System.out.println("Retour au menu principal.");
                    break;
                case "7":
                    System.out.println("Quitter.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

}
