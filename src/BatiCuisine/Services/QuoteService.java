package BatiCuisine.Services;

import BatiCuisine.Entities.Quote;
import BatiCuisine.Repository.Implementation.QuoteRepository;

import java.util.List;
import java.util.Optional;

public class QuoteService {
    private QuoteRepository quoteRepository;

    public QuoteService() {
        this.quoteRepository = new QuoteRepository();
    }
    public Quote save(Quote quote){
        return quoteRepository.save(quote);
    }
    public Optional<Quote> findById(int id){
        return quoteRepository.findById(id);
    }
    public Optional<Quote> findQuoteByProjectId(int projectId){
        return quoteRepository.findByProjectId(projectId);
    }
    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }
    public Quote update(Quote quote){
        return quoteRepository.update(quote);
    }
    public Boolean delete(int id){
        return quoteRepository.delete(id);
    }
}
