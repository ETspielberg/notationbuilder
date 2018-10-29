package unidue.ub.servicerunner.repository.journalHoldings;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.journalHoldings.Journal;

@RepositoryRestResource(collectionResourceRel = "journal", path = "journal")
public interface JournalRepository extends CrudRepository<Journal, Long> {
}
