package org.unidue.ub.libintel.adminbackend.repository.setup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.setup.UserCategory;

@RepositoryRestResource(collectionResourceRel = "userCategory", path = "userCategory")
public interface UserCategoryRepository extends PagingAndSortingRepository<UserCategory,String> {
}
