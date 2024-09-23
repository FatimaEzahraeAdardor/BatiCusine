package BatiCuisine.Views;

import BatiCuisine.Entities.Project;
import BatiCuisine.Entities.Quote;
import BatiCuisine.Services.ProjectService;
import BatiCuisine.Services.QuoteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class QuoteView {
    private QuoteService quoteService;
    private ProjectService projectService;
    private Scanner scanner;

    public QuoteView() {
        this.quoteService = new QuoteService();
        this.projectService = new ProjectService();
        this.scanner = new Scanner(System.in);
    }
    public void AddQuote(Project project){
        System.out.println("----- Enregistrement de devis ----");
        Double estimatedAmount =  project.getTotalCost();

        System.out.print("Enter issue date (yyyy-MM-dd): ");
        LocalDate issueDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter validated date (yyyy-MM-dd): ");
        LocalDate validatedDate = LocalDate.parse(scanner.nextLine());

        Quote quote = new Quote( estimatedAmount, issueDate, validatedDate, false, project);
        quoteService.save(quote);
        System.out.println("Citation enregistrée avec succès!");
    }
    public void displayAllQuotes() {
        System.out.printf("Entrer ID de projet: ");
        int projetId = scanner.nextInt();
        Optional<Project> project = projectService.findById(projetId);

        if (project.isEmpty()) {
            System.out.println("Projet avec ID " + projetId + " non trouvé.");
            return;
        }
        System.out.println("--- Liste de tous les devis ---");
        List<Quote> quotes = quoteService.findQuoteByProjectId(projetId);

        if (quotes.isEmpty()) {
            System.out.println("Aucun devis trouvé pour ce projet.");
        } else {
            System.out.println("\n+------+------------------+--------------------------+---------------------+------------------+");
            System.out.printf("| %-4s | %-16s | %-24s | %-20s | %-16s |\n",
                    "ID", "Montant Estimé", "Date d'Émission", "Statut", "Nom du Projet");
            System.out.println("+------+------------------+--------------------------+---------------------+------------------+");

            for (Quote quote : quotes) {
                System.out.printf("| %-4s | %-16.2f | %-24s | %-20s | %-16s |\n",
                        quote.getId(),
                        quote.getEstimatedAmount(),
                        quote.getIssueDate(),
                        quote.isAccepted() ? "Accepté" : "Refusé",
                        quote.getProject().getProjectName());
                System.out.println("+------+------------------+--------------------------+---------------------+------------------+");
            }
        }
    }

    public void delete() {
        System.out.print("Entrer nom de devis ");
        int id = scanner.nextInt();
        scanner.nextLine();
        quoteService.delete(id);
    }
}
