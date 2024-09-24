package BatiCuisine.Views;

import BatiCuisine.Entities.Client;
import BatiCuisine.Services.ClientService;
import BatiCuisine.utils.InputValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientView {
    ClientService clientService = new ClientService();
    Scanner scanner = new Scanner(System.in);

    public Client create() {
        System.out.println("-------------------- Ajout d'un nouveau client ---------------------");
        String name = InputValidator.promptForString("Entrez le nom du client :");
        String address = InputValidator.promptForString("Entrez l'adresse du client : ");
        String phone = InputValidator.promptForString("Entrez le numéro de téléphone du client : ");
        String choice = InputValidator.promptForString("Choisissez si le client est professionnel (oui/non) :");
        boolean isProfessional;

        if (choice.equalsIgnoreCase("oui")) {
            isProfessional = true;
        } else if (choice.equalsIgnoreCase("non")) {
            isProfessional = false;
        } else {
            System.out.println("Choix invalide. Le client sera enregistré comme non professionnel par défaut.");
            isProfessional = false;
        }

        Client client = new Client(name, address, phone, isProfessional);
        Client client1 = clientService.save(client);
        if (client1 != null){
            System.out.println("Nouveau client ajouté !");
            System.out.println("Nom : " + name);
            System.out.println("Adresse : " + address);
            System.out.println("Téléphone : " + phone);
            return client1;
        }else {
            System.out.println("Échec de la création du client. Le nom est peut-être déjà utilisé.\n");
        }
        return null;
    }

    public void update(){
        System.out.println("-------------------- Modifier un client ---------------------------");
        String nom = InputValidator.promptForString("Entrez le nom du client à modifier : ");
        Optional<Client> existingClient = clientService.findByName(nom);
        if (existingClient.isPresent()) {
            Client client = existingClient.get();

            System.out.print("Modifier le nom actuel : " + client.getName() + "(ou appuyez sur Entrée pour conserver l'ancien) : ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                client.setName(name);
            }
            System.out.print("Modifier l'adresse actuelle : " + client.getAddress() + "(ou appuyez sur Entrée pour conserver l'ancien) : ");
            String address = scanner.nextLine();
            if (!address.trim().isEmpty()) {
                client.setAddress(address);
            }

            System.out.print("Modifier le téléphone actuel : " + client.getPhone() + "(ou appuyez sur Entrée pour conserver l'ancien) : ");
            String phone = scanner.nextLine();
            if (!phone.trim().isEmpty()) {
                client.setPhone(phone);
            }
            System.out.print("Le client est-il professionnel actuel : " + (client.isProfessional() ? "Oui" : "Non") + "(ou appuyez sur Entrée pour conserver l'ancien) ?");
            System.out.print("1. Oui");
            System.out.print("2. Non");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("oui")) {
                client.setProfessional(true);
            } else if (choice.equalsIgnoreCase("non")) {
                client.setProfessional(false);
            } else {
                System.out.println("Choix invalide. Statut professionnel inchangé.");
            }
            Client updatedClient = clientService.update(client);
            System.out.println("Client mis à jour avec succès !");
            System.out.println("Nom : " + updatedClient.getName());
            System.out.println("Adresse : " + updatedClient.getAddress());
            System.out.println("Téléphone : " + updatedClient.getPhone());
        } else {
            System.out.println("Aucun client trouvé avec le nom : " + nom);
        }
    }
    public void delete(){
        System.out.println("-------------------- Supprimer un client ---------------------------");
        int id = InputValidator.promptForInteger("Entrez id du client à supprimer : ");
        Optional<Client> existingClient = clientService.findById(id);
        if (existingClient.isPresent()) {
            clientService.delete(id);
            System.out.println("Client supprimé avec succès !");
        } else {
            System.out.println("Aucun client trouvé avec l'identifiant : " + id);
        }
    }
    public Client searchClientByName() {
        String name = InputValidator.promptForString("Entrez le nom du client :");
        Optional<Client> client = clientService.findByName(name);
        if (client.isPresent()) {
            Client foundClient = client.get();
            System.out.println("Client trouvé !");
            System.out.println("Nom : " + foundClient.getName());
            System.out.println("Adresse : " + foundClient.getAddress());
            System.out.println("Téléphone : " + foundClient.getPhone());
            return foundClient;
        } else {
            System.out.println("Aucun client trouvé avec ce nom.");
            return null;
        }
    }

    public void displayAllClient() {
        System.out.println("-------------------- Liste de tous les clients ---------------------");
        List<Client> clients = clientService.findAll();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else{
            System.out.println("\n+------+------------------+--------------------------+---------------------+------------------+");
            System.out.printf("| %-4s | %-16s | %-24s |%-20s | %-16s |\n","ID", "Nom", "Address", "Telephone", "Professionel");
            System.out.println("+------+------------------+--------------------------+---------------------+------------------+");
            for (Client client : clients) {
                System.out.printf("| %-4s | %-16s | %-24s |%-20s | %-16s |\n", client.getId(), client.getName(), client.getAddress(), client.getPhone(), client.isProfessional()?"oui":"non");
                System.out.println("+------+------------------+--------------------------+---------------------+------------------+");

            }
        }
    }
}
