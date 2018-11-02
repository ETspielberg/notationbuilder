package unidue.ub.servicerunner.model.journals;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Journalholding {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "CONTAINS", direction = Relationship.UNDIRECTED)
    private Set<Journalcollection> journalcollections;

    @Relationship(type = "CONTAINS", direction = Relationship.UNDIRECTED)
    private Set<Journal> journals;

    private String name;

    private String description;

    public Journalholding() {
        this.name = "";
        this.description = "";
        this.journalcollections = new HashSet<>();
        this.journals = new HashSet<>();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addJournalcollection(Journalcollection journalcollection) {
        this.journalcollections.add(journalcollection);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Journalholding)) {
            return false;
        }
        Journalholding journalholding = (Journalholding) o;
        return Objects.equals(description, journalholding.description) &&
                Objects.equals(name, journalholding.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
