package org.unidue.ub.libintel.adminbackend.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Conditions {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Boolean hasArchive;

    private String simultaneousUsers;

    public Conditions() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasArchive() {
        return hasArchive;
    }

    public void setHasArchive(Boolean hasArchive) {
        this.hasArchive = hasArchive;
    }

    public String getSimultaneousUsers() {
        return simultaneousUsers;
    }

    public void setSimultaneousUsers(String simultaneousUsers) {
        this.simultaneousUsers = simultaneousUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Conditions)) {
            return false;
        }
        Conditions conditions = (Conditions) o;
        return hasArchive == conditions.hasArchive &&
                Objects.equals(description, conditions.description) &&
                Objects.equals(simultaneousUsers, conditions.simultaneousUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasArchive, description, simultaneousUsers);
    }
}
