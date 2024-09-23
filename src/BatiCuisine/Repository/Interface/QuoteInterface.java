package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteInterface {
    Quote save(Quote quote) ;

    List<Quote> findByProjectId(int id);

    List<Quote> findAll() ;

    Quote update(Quote quote);

    Boolean delete(int id);
}
