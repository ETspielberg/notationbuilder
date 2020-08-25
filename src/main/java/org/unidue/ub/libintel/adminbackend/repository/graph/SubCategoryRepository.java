package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.systematic.SubCategory;

@RepositoryRestResource(collectionResourceRel = "subCategory", path = "subCategory")
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
}
