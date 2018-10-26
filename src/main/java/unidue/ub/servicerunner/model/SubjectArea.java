package unidue.ub.servicerunner.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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
    private List<Description> descriptions;

    @Relationship(type = "HAS_COMMENTS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "comment")
    private List<Comment> comments;

    @Relationship(type = "HAS_SUB_CATEGORIES", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "subCategory")
    private List<SubCategory> subCategorys;

    @Relationship(type = "HAS_CLASSIFICATIONS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "classification")
    private List<Classification> classifications;

    public SubjectArea() { }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void hasComment(Comment comment) { this.comments.add(comment);}

    public List<SubCategory> getSubCategorys() {
        return subCategorys;
    }

    public void setSubCategorys(List<SubCategory> subCategorys) {
        this.subCategorys = subCategorys;
    }

    public void hasSubCategorys(SubCategory subCategory) {this.subCategorys.add(subCategory);}

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public void hasClassification(Classification classification) {this.classifications.add(classification);}
}
