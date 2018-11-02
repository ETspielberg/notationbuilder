package unidue.ub.servicerunner.repository.setup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.setup.ItemGroup;

@RepositoryRestResource(collectionResourceRel = "itemGroup", path = "itemGroup")
public interface ItemGroupRepository extends PagingAndSortingRepository<ItemGroup,String> {

}
