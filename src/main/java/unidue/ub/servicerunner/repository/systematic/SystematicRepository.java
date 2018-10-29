package unidue.ub.servicerunner.repository.systematic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.systematic.Systematic;

@RepositoryRestResource(collectionResourceRel = "systematic", path = "systematic")
public interface SystematicRepository extends CrudRepository<Systematic, Long> {
}
