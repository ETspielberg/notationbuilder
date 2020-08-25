package org.unidue.ub.libintel.adminbackend.model.journals;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

@RelationshipEntity(type="GIVES_ACCESS_TO")
public class JournalcollectionAccess {

    @Id
    private Long relationshipId;

    @Property
    private Date from;

    @Property
    private Date till;

    @StartNode
    private Contract contract;

    @EndNode
    private Journalcollection journalcollection;

    public JournalcollectionAccess() {}

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTill() {
        return till;
    }

    public void setTill(Date till) {
        this.till = till;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Journalcollection getJournalcollection() {
        return journalcollection;
    }

    public void setJournalcollection(Journalcollection journalcollection) {
        this.journalcollection = journalcollection;
    }
}
