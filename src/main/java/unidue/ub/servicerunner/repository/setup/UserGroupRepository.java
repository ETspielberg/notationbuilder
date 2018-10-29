package unidue.ub.servicerunner.repository.setup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.settings.fachref.UserGroup;

@RepositoryRestResource(collectionResourceRel = "userGroup", path = "userGroup")
public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup,String> {

}
