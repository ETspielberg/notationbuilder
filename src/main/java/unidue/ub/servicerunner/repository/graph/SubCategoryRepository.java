package unidue.ub.servicerunner.repository.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.systematic.SubCategory;

@RepositoryRestResource(collectionResourceRel = "subCategory", path = "subCategory")
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
}
