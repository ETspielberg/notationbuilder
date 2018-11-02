package unidue.ub.servicerunner.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Phone {

    @Id
    @GeneratedValue
    private Long Id;

    private String type;

    private String phone;

    public Phone() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Phone)) {
            return false;
        }
        Phone phone = (Phone) o;
        return Objects.equals(phone, phone.phone) &&
                Objects.equals(type, phone.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, phone);
    }
}
