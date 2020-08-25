package org.unidue.ub.libintel.adminbackend.repository.setup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.setup.ItemCategory;


@RepositoryRestResource(collectionResourceRel = "itemCategory", path = "itemCategory")
public interface ItemCategoryRepository extends PagingAndSortingRepository<ItemCategory,String> {

}
