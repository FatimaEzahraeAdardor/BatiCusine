package BatiCuisine.Views;

import BatiCuisine.Entities.Client;
import BatiCuisine.Services.ClientService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientView {
    ClientService clientService = new ClientService();
    Scanner scanner = new Scanner(System.in);

    public Client create() {
        System.out.println("--- Ajout d'un nouveau client ---");
        System.out.print("Entrez le nom du client : ");
        String name = scanner.nextLine();
        System.out.print("Entrez l'adresse du client : ");
        String address = scanner.nextLine();
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phone = scanner.nextLine();
        System.out.println("Choisissez si le client est professionnel (oui/non) :");
        String choice = scanner.nextLine();
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
        clientService.save(client);
        if (client != null){
            System.out.println("Nouveau client ajouté !");
            System.out.println("Nom : " + name);
            System.out.println("Adresse : " + address);
            System.out.println("Téléphone : " + phone);
//            System.out.println("Vous pouvez maintenant créer le projet pour " + name + ".");
            return client;
        }else {
            System.out.println("Échec de la création du client. Le nom est peut-être déjà utilisé.");
        }
        return null;
    }

    public void update(){
        System.out.println("--- Modifier un client ---");
        System.out.print("Entrez le nom du client à modifier : ");
        String nom = scanner.nextLine();
        Optional<Client> existingClient = clientService.findByName(nom);
        if (existingClient.isPresent()) {
            Client client = existingClient.get();

            System.out.print("Modifier le nom (actuel : " + client.getName() + ") : ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                client.setName(name);
            }

            System.out.print("Modifier l'adresse (actuelle : " + client.getAddress() + ") : ");
            String address = scanner.nextLine();
            if (!address.trim().isEmpty()) {
                client.setAddress(address);
            }

            System.out.print("Modifier le téléphone (actuel : " + client.getPhone() + ") : ");
            String phone = scanner.nextLine();
            if (!phone.trim().isEmpty()) {
                client.setPhone(phone);
            }
            System.out.print("Le client est-il professionnel (actuel : " + (client.isProfessional() ? "Oui" : "Non") + ") ?");
            System.out.print("1. Oui");
            System.out.print("2. Non");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("oui")) {
                client.setProfessional(true);
            } else if (choice.equalsIgnoreCase("non")) {
                client.setProfessional(false);
            } else {
                System.out.println("Choix invalide. Statut professionnel inchangé.");
            }
            clientService.update(client);
            System.out.println("Client mis à jour avec succès !");
            System.out.println("Nom : " + name);
            System.out.println("Adresse : " + address);
            System.out.println("Téléphone : " + phone);
//            System.out.println("Vous pouvez maintenant créer le projet pour " + name + ".");
        } else {
            System.out.println("Aucun client trouvé avec le nom : " + nom);
        }
    }
    public void delete(){
        System.out.println("--- Supprimer un client ---");
        System.out.print("Entrez id du client à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Client> existingClient = clientService.findById(id);
        if (existingClient.isPresent()) {
            clientService.delete(id);
            System.out.println("Client supprimé avec succès !");
        } else {
            System.out.println("Aucun client trouvé avec l'identifiant : " + id);
        }
    }
    public Client searchClientByName() {
        System.out.print("Entrez le nom du client :");
        String name = scanner.nextLine();
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
        System.out.println("--- Liste de tous les clients ---");
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
