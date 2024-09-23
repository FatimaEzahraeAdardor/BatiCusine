package BatiCuisine.Repository.Interface;

import BatiCuisine.Entities.Client;
import BatiCuisine.Entities.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteInterface {
    Quote save(Quote quote) ;
    Optional<Quote> findById(int id);

    List<Quote> findByProjectId(int projectId);

    List<Quote> findAll() ;

    Quote update(Quote quote);

    Boolean delete(int id);
}
