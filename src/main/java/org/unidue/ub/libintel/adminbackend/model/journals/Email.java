package org.unidue.ub.libintel.adminbackend.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Email {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private String email;

    public Email() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Email)) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(type, email.type) &&
                Objects.equals(email, email.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, email);
    }
}
