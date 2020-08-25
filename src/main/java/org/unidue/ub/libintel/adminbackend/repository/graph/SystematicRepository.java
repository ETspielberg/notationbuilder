package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.systematic.Systematic;

@RepositoryRestResource(collectionResourceRel = "systematic", path = "systematic")
public interface SystematicRepository extends CrudRepository<Systematic, Long> {
}
