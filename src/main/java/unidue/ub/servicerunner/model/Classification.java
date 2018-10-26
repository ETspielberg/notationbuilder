package unidue.ub.servicerunner.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NodeEntity
@XmlRootElement(name="classification")
public class Classification {

    @Id
    @GeneratedValue
    private Long id;

    private String reference;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.DIRECTION)
    @XmlElement(name = "description")
    private List<Description> descriptions;

    private String target;

    @Relationship(type = "HAS_COMMENTS", direction = Relationship.DIRECTION)
    @XmlElement(name = "comment")
    private List<Comment> comments;

    public Classification() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void hasComment(Comment comment) { this.comments.add(comment);}

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void hasDescription(Description description) { this.descriptions.add(description); }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
