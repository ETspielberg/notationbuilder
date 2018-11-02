package unidue.ub.servicerunner.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Publisher {

    @Id
    @GeneratedValue
    private Long id;

    private Set<String> platforms;

    private String address;

    private String email;

    private String internalId;

    @Relationship(type = "HAS_CONTACTPERSONS")
    private Set<Contactperson> contactpeople;

    public Publisher() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<String> platforms) {
        this.platforms = platforms;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public Set<Contactperson> getContactpeople() {
        return contactpeople;
    }

    public void setContactpeople(Set<Contactperson> contactpeople) {
        this.contactpeople = contactpeople;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Publisher)) {
            return false;
        }
        Publisher publisher = (Publisher) o;
        return Objects.equals(platforms, publisher.platforms) &&
                Objects.equals(address, publisher.address) &&
                Objects.equals(email, publisher.email) &&
                Objects.equals(internalId, publisher.internalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platforms, address, email, internalId);
    }
}
