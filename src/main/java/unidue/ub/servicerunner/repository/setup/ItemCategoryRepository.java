package unidue.ub.servicerunner.repository.setup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.setup.ItemCategory;


@RepositoryRestResource(collectionResourceRel = "itemCategory", path = "itemCategory")
public interface ItemCategoryRepository extends PagingAndSortingRepository<ItemCategory,String> {

}
