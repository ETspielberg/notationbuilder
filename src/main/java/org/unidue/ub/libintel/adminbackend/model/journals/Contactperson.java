package org.unidue.ub.libintel.adminbackend.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Contactperson {

    @Id
    @GeneratedValue
    private Long Id;

    private String surname;

    private String firstname;

    private String middlenames;

    @Relationship(type = "HAS_EMAILS")
    private Set<Email> emails;

    @Relationship(type = "HAS_PHONES")
    private Set<Phone> phones;

    @Relationship(type = "HAS_DEPUTY", direction = Relationship.UNDIRECTED)
    private Set<Contactperson> deputy;

    public Contactperson() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlenames() {
        return middlenames;
    }

    public void setMiddlenames(String middlenames) {
        this.middlenames = middlenames;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Contactperson> getDeputy() {
        return deputy;
    }

    public void setDeputy(Set<Contactperson> deputy) {
        this.deputy = deputy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Contactperson)) {
            return false;
        }
        Contactperson contactperson = (Contactperson) o;
        return  Objects.equals(contactperson, contactperson.surname) &&
                Objects.equals(contactperson, contactperson.firstname) &&
                Objects.equals(contactperson, contactperson.middlenames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, firstname, middlenames);
    }
}
