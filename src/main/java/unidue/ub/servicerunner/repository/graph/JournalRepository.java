package unidue.ub.servicerunner.repository.graph;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import unidue.ub.servicerunner.model.journals.Journal;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "journal", path = "journal")
public interface JournalRepository extends Neo4jRepository<Journal, Long> {

    Optional<Journal> findByName(String name);
}
