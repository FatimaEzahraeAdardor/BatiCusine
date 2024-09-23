import BatiCuisine.Config.DataBaseConnection;
import BatiCuisine.Views.ClientView;
import BatiCuisine.Views.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");
        menu.menuPrincipal();
    }

}
