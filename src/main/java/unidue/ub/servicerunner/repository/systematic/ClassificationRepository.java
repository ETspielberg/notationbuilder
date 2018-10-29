package unidue.ub.servicerunner.repository.systematic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.systematic.SubjectArea;

@RepositoryRestResource(collectionResourceRel = "classification", path = "classification")
public interface ClassificationRepository extends CrudRepository<SubjectArea, Long> {

}
