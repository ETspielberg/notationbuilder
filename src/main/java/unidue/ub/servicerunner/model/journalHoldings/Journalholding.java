package unidue.ub.servicerunner.model.journalHoldings;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@NodeEntity
public class Journalholding {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
    private Set<Journalcollection> journalcollections;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
    private Set<Journal> journals;

    private String anchor;

    private String desription;

    public Journalholding() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Journalcollection> getJournalcollections() {
        return journalcollections;
    }

    public void setJournalcollections(Set<Journalcollection> journalcollections) {
        this.journalcollections = journalcollections;
    }

    public Set<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Set<Journal> journals) {
        this.journals = journals;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public void addJournalcollection(Journalcollection journalcollection) {
        this.journalcollections.add(journalcollection);
    }
}
