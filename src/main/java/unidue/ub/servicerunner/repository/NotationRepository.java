package unidue.ub.servicerunner.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.settings.fachref.Notation;

import java.util.List;

/*
@RepositoryRestResource(collectionResourceRel = "notation", path = "notation")
public interface NotationRepository  extends PagingAndSortingRepository<Notation, String>{

    @Query(value = "SELECT p.* FROM Notation p WHERE SUBSTRING(p.notation,1,3) BETWEEN :startNotation AND :endNotation", nativeQuery = true)
    List<Notation> getNotationList(@Param("startNotation") String startNotation, @Param("endNotation") String endNotation);

    @Query(value = "SELECT n.* FROM Notation n, Notationgroup ng WHERE  ng.notationgroup_name = :identifier AND SUBSTRING(n.notation,1,3) BETWEEN ng.notations_start AND ng.notations_end" , nativeQuery = true)
    List<Notation> getNotationListForNotationgroup(@Param("identifier") String identifier);

}
*/
