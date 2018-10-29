package unidue.ub.servicerunner.model.systematic;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.*;
import java.util.Set;

@NodeEntity
@XmlRootElement(name="subjectArea")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubjectArea {

    @Id
    @GeneratedValue
    private Long id;

    private String reference;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "description")
    @XmlElementWrapper(name = "descriptions")
    private Set<Description> descriptions;

    @Relationship(type = "HAS_COMMENTS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "comment")
    @XmlElementWrapper(name = "comments")
    private Set<Comment> comments;

    @Relationship(type = "HAS_SUB_CATEGORIES", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "subCategory")
    @XmlElementWrapper(name = "subCategorys")
    private Set<SubCategory> subCategorys;

    @Relationship(type = "HAS_CLASSIFICATIONS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "classification")
    @XmlElementWrapper(name = "classifications")
    private Set<Classification> classifications;

    public SubjectArea() { }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public void hasDescription(Description description) { this.descriptions.add(description);}

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void hasComment(Comment comment) { this.comments.add(comment);}

    public Set<SubCategory> getSubCategorys() {
        return subCategorys;
    }

    public void setSubCategorys(Set<SubCategory> subCategorys) {
        this.subCategorys = subCategorys;
    }

    public void hasSubCategorys(SubCategory subCategory) {this.subCategorys.add(subCategory);}

    public Set<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(Set<Classification> classifications) {
        this.classifications = classifications;
    }

    public void hasClassification(Classification classification) {this.classifications.add(classification);}
}
