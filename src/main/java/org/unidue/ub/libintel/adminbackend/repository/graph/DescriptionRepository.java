package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.systematic.Description;

@RepositoryRestResource(collectionResourceRel = "description", path = "description")
public interface DescriptionRepository extends CrudRepository<Description, Long> {
}
