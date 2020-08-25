package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.systematic.SubjectArea;

@RepositoryRestResource(collectionResourceRel = "classification", path = "classification")
public interface ClassificationRepository extends CrudRepository<SubjectArea, Long> {

}
