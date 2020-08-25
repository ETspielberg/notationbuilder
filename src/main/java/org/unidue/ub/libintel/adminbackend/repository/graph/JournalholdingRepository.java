package org.unidue.ub.libintel.adminbackend.repository.graph;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.unidue.ub.libintel.adminbackend.model.journals.Journalholding;

import java.util.Optional;

@Repository
public interface JournalholdingRepository extends Neo4jRepository<Journalholding, Long> {

    Optional<Journalholding> findByName(String name);
}
