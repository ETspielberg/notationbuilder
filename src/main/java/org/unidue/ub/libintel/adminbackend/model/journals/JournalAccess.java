package org.unidue.ub.libintel.adminbackend.model.journals;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type="GIVES_ACCESS_TO")
public class JournalAccess {

    @Id
    private Long relationshipId;

    @Property
    private Date from;

    @Property
    private Date till;

    @StartNode
    private Contract contract;

    @EndNode
    private Journal journal;
}
