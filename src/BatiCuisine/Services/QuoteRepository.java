package BatiCuisine.Services;

import BatiCuisine.Entities.Quote;

import java.util.Optional;

public class QuoteRepository {
    private QuoteRepository quoteRepository;

    public QuoteRepository() {
        this.quoteRepository = new QuoteRepository();
    }
    public Quote save(Quote quote){
      return quoteRepository.save(quote);
    }
    public Optional<Quote> findById(int id){
        return quoteRepository.findById(id);
    }
    public Quote update(Quote quote){
        return quoteRepository.update(quote);
    }
    public Boolean delete(int id){
        return quoteRepository.delete(id);
    }

}
