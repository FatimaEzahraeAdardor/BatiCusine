package BatiCuisine.Views;

import BatiCuisine.Entities.Project;
import BatiCuisine.Entities.Quote;
import BatiCuisine.Services.ProjectService;
import BatiCuisine.Services.QuoteService;

import java.time.LocalDate;
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
    public void delete() {
        System.out.print("Entrer nom de devis ");
        int id = scanner.nextInt();
        scanner.nextLine();
        quoteService.delete(id);
    }
}
