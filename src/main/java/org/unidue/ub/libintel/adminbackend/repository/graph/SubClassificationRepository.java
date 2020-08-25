package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.systematic.SubClassification;

@RepositoryRestResource(collectionResourceRel = "subClassification", path = "subClassification")
public interface SubClassificationRepository extends CrudRepository<SubClassification, Long> {
}
