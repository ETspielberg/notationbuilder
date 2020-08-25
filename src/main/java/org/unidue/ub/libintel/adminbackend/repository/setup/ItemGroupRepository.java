package org.unidue.ub.libintel.adminbackend.repository.setup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.setup.ItemGroup;

@RepositoryRestResource(collectionResourceRel = "itemGroup", path = "itemGroup")
public interface ItemGroupRepository extends PagingAndSortingRepository<ItemGroup,String> {

}
