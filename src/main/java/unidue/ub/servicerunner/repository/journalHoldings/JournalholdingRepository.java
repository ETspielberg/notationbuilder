package unidue.ub.servicerunner.repository.journalHoldings;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.journalHoldings.Journalholding;

@RepositoryRestResource(collectionResourceRel = "journalholding", path = "journalholding")
public interface JournalholdingRepository extends CrudRepository<Journalholding, Long> {
}
