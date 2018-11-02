package unidue.ub.servicerunner.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.systematic.SubClassification;

@RepositoryRestResource(collectionResourceRel = "subClassification", path = "subClassification")
public interface SubClassificationRepository extends CrudRepository<SubClassification, Long> {
}
