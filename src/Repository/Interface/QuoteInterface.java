package Repository.Interface;

import Entities.Client;
import Entities.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteInterface {
    Quote save(Quote quote) ;
    Optional<Quote> findById(int id);

    Optional<Quote> findByProjectId(int projectId);

    List<Quote> findAll() ;

    Quote update(Quote quote);

    Boolean delete(int id);
}
