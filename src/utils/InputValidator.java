package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {
   private static Scanner scanner = new Scanner(System.in);

    public static double promptForDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre decimal valide.");
            }
        }
    }
    public static LocalDate promptForDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(promptForString(""), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Veuillez entrer une date valide au format yyyy-MM-dd.");
            }
        }
    }
    public static int promptForInteger(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
            }
        }
    }
    public static String promptForString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Veuillez entrer une chaîne de caractères non vide.");
            }
        }
    }

}
