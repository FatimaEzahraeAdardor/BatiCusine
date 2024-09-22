import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Views.ClientView;
import BatiCuisine.Views.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
//        DataBaseConnection dbConnection = DataBaseConnection.getInstance();
//        Connection connection = DataBaseConnection.getConnection();
//        if (connection != null) {
//            System.out.println("La connexion à la base de données est réussie.");
//        } else {
//            System.out.println("La connexion à la base de données a échoué.");
//        }
        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");
        menu.menuPrincipal();
    }

}
