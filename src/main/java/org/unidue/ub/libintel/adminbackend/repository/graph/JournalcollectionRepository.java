package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.unidue.ub.libintel.adminbackend.model.journals.Journalcollection;

import java.util.Iterator;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "journalcollection", path = "journalcollection")
public interface JournalcollectionRepository extends Neo4jRepository<Journalcollection, Long> {

    Optional<Journalcollection> findByIdentifier(String identifier);

    @Query("match (r:Journalcollection) --> (s:Journal) --> (v:Journaltitle {issn: {issn}}) return (r)")
    Iterator<Journalcollection[]> findByIssn(String issn);
}
