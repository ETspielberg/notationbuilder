package unidue.ub.servicerunner.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="subCategory")
@NodeEntity
public class SubCategory {

    @Id
    @GeneratedValue
    private Long id;

    private String target;

    private String reference;

    @Relationship(type = "HAS_COMMENTS", direction = Relationship.DIRECTION)
    @XmlElement(name = "comment")
    private List<Comment> comments;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.DIRECTION)
    @XmlElement(name = "description")
    private List<Description> descriptions;

    @Relationship(type = "HAS_SUB_CATEGORIES", direction = Relationship.DIRECTION)
    @XmlElement(name = "subCategory")
    private List<SubCategory> subCategorys;

    @Relationship(type = "HAS_CLASSIFICATIONS", direction = Relationship.DIRECTION)
    @XmlElement(name = "classification")
    private List<Classification> classifications;

    public SubCategory() {}

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void hasComment(Comment comment) {this.comments.add(comment);}

    public void setSubCategorys(List<SubCategory> subCategorys) {
        this.subCategorys = subCategorys;
    }

    public void hasSubCategory(SubCategory subCategory) { this.subCategorys.add(subCategory);}

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public void hasDescription(Description description) { this.descriptions.add(description);}

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public void hasClassification(Classification classification) {this.classifications.add(classification);}
}
