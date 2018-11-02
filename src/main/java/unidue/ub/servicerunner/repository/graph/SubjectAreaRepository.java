package unidue.ub.servicerunner.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.systematic.SubjectArea;

@RepositoryRestResource(collectionResourceRel = "subjectArea", path = "subjectArea")
public interface SubjectAreaRepository extends CrudRepository<SubjectArea, Long> {

}
