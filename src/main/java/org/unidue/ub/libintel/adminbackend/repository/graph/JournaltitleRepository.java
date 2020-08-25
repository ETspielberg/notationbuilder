package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.journals.Journaltitle;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "journaltitle", path = "journaltitle")
public interface JournaltitleRepository extends Neo4jRepository<Journaltitle, Long> {

    @Query("MATCH (s:Journaltitle)-[r:IS_SAME_JOURNAL]- (t)" +
            "WHERE s.issn={0} " +
            "RETURN t.issn")
    Set<String> getRelatedIssnsFor(String issn);

    Optional<Journaltitle> findByName(String name);
}

