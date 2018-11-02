package unidue.ub.servicerunner.repository.graph;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.journals.Journalcollection;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "journalcollection", path = "journalcollection")
public interface JournalcollectionRepository extends Neo4jRepository<Journalcollection, Long> {

    Optional<Journalcollection> findByIdentifier(String identifier);
}
