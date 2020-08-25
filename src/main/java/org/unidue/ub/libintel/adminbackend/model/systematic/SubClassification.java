package org.unidue.ub.libintel.adminbackend.model.systematic;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@NodeEntity
@XmlRootElement(name="subClassification")
public class SubClassification {

    @Id
    @GeneratedValue
    private Long id;

    private String target;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "description")
    @XmlElementWrapper(name = "descriptions")
    private List<Description> descriptions;

    private String reference;

    @Relationship(type = "HAS_COMMENTS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "comment")
    @XmlElementWrapper(name = "comments")
    private List<Comment> comments;

    public SubClassification() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public void hasDescription(Description description) { this.descriptions.add(description);}


    public void setReference(String reference) {
        this.reference = reference;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void hasComment(Comment comment) { this.comments.add(comment);}

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SubClassification)) {
            return false;
        }
        SubClassification subClassification = (SubClassification) o;
        return Objects.equals(target, subClassification.target) &&
                Objects.equals(reference, subClassification.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, reference);
    }
}
