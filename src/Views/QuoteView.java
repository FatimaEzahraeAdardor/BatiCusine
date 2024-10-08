package Views;

import Entities.Client;
import Entities.Project;
import Entities.Quote;
import Enums.ProjectStatus;
import Services.ProjectService;
import Services.QuoteService;
import utils.InputValidator;

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
    public void AddQuote(Project project) {
        System.out.println("\n------------------ Enregistrement de devis -----------------------\n");
        Double estimatedAmount = project.getTotalCost();
        LocalDate issueDate = InputValidator.promptForDate("Entrez la date d'emission (yyyy-MM-dd) : ");
        LocalDate validatedDate;
        while (true) {
            validatedDate = InputValidator.promptForDate("Entrez la date de validite (yyyy-MM-dd) : ");
            if (validatedDate.isAfter(issueDate) || validatedDate.isEqual(issueDate)) {
                break;
            } else {
                System.out.println("La date de validite ne peut pas être anferieure  la date d'emission. Veuillez entrer une date valide.");
            }
        }
        Quote quote = new Quote(estimatedAmount, issueDate, validatedDate, false, project);
        quoteService.save(quote);

        System.out.println("Devis enregistre avec succès !");
    }


    public void displayQuoteByProject() {
        int projectId = InputValidator.promptForInteger("Entrer ID de projet: ");
        Optional<Project> project = projectService.findById(projectId);

        if (project.isEmpty()) {
            System.out.println("Projet avec ID " + projectId + " non trouve.");
            return;
        }
        System.out.println("--- Liste de tous les devis ---");
        Optional<Quote> quote = quoteService.findQuoteByProjectId(projectId);

        if (quote.isEmpty()) {
            System.out.println("Aucun devis trouve pour ce projet.");
        } else {
            System.out.println("\n+------+------------------+--------------------------+---------------------+------------------+");
            System.out.printf("| %-4s | %-16s | %-24s | %-20s | %-16s |\n",
                    "ID", "Montant Estime", "Date d'emission", "Statut", "Nom du Projet");
            System.out.println("+------+------------------+--------------------------+---------------------+------------------+");
             System.out.printf("| %-4s | %-16.2f | %-24s | %-20s | %-16s |\n",
                        quote.get().getId(),
                        quote.get().getEstimatedAmount(),
                        quote.get().getIssueDate(),
                        quote.get().isAccepted() ? "Accepte" : "Refuse",
                        quote.get().getProject().getProjectName());
                System.out.println("+------+------------------+--------------------------+---------------------+------------------+");
            }
    }
    public void displayAllQuotes() {
        System.out.println("--- Liste de tous les devis ---");
        List<Quote> quotes = quoteService.findAll();
        if (quotes.isEmpty()) {
            System.out.println("Aucun devis trouve pour ce projet.");
        } else {
            System.out.println("\n+------+------------------+--------------------------+---------------------+------------------+");
            System.out.printf("| %-4s | %-16s | %-24s | %-20s | %-16s |\n",
                    "ID", "Montant Estime", "Date d'emission", "Statut", "Nom du Projet");
            System.out.println("+------+------------------+--------------------------+---------------------+------------------+");

            for (Quote quote : quotes) {
                System.out.printf("| %-4s | %-16.2f | %-24s | %-20s | %-16s |\n",
                        quote.getId(),
                        quote.getEstimatedAmount(),
                        quote.getIssueDate(),
                        quote.isAccepted() ? "Accepte" : "Refuse",
                        quote.getProject().getProjectName());
                System.out.println("+------+------------------+--------------------------+---------------------+------------------+");
            }
        }
    }
public void AccepterQuote() {
    int id = InputValidator.promptForInteger("Entrer ID de devis : ");
    System.out.print("Souhaitez-vous accepter ce devis ? (y/n) : ");
    String response = scanner.nextLine();

    boolean accepteQuote = response.equalsIgnoreCase("y");
    Optional<Quote> optionalQuote = quoteService.findById(id);

    if (optionalQuote.isPresent()) {
        Quote quote = optionalQuote.get();

        if (accepteQuote) {
            quote.setAccepted(true);
            quoteService.update(quote);
            System.out.println("Devis accepte avec succès.");
            Project project = quote.getProject();
            if (project != null) {
                project.setStatus(ProjectStatus.INPROGRESS);
                projectService.update(project);
                System.out.println("Statut du projet mis  jour en ACTIVE.");
            }
        } else {
            System.out.println("Devis non accepte.");
            Project project = quote.getProject();
            if (project != null) {
                project.setStatus(ProjectStatus.CANCELLED);
                projectService.update(project);
                System.out.println("Statut du projet mis  jour en CANCELLED.");
            }
        }
    } else {
        System.out.println("Devis non trouve pour l'ID donne.");
    }
}



    public void delete() {
        int id = InputValidator.promptForInteger("Entrer ID de devi: ");
        Optional<Quote> existingDevi = quoteService.findById(id);
        if (existingDevi.isPresent()) {
            quoteService.delete(id);
            System.out.println("Devi supprime avec succès !");
        } else {
            System.out.println("Aucun devi trouve avec l'identifiant : " + id);
        }
    }
}
