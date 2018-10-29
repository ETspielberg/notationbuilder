package unidue.ub.servicerunner.repository.journalHoldings;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.journalHoldings.Journaltitle;

@RepositoryRestResource(collectionResourceRel = "journalcollection", path = "journalcollection")
public interface JournalcollectionRepository extends CrudRepository<Journaltitle, Long> {
}