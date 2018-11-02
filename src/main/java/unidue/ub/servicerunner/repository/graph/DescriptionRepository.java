package unidue.ub.servicerunner.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.systematic.Description;

@RepositoryRestResource(collectionResourceRel = "description", path = "description")
public interface DescriptionRepository extends CrudRepository<Description, Long> {
}
