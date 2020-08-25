package org.unidue.ub.libintel.adminbackend.model.systematic;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.Set;

@XmlRootElement(name="subCategory")
@NodeEntity
public class SubCategory {

    @Id
    @GeneratedValue
    private Long id;

    private String target;

    private String reference;

    @Relationship(type = "HAS_COMMENTS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "comment")
    @XmlElementWrapper(name = "comments")
    private Set<Comment> comments;

    @Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "description")
    @XmlElementWrapper(name = "descriptions")
    private Set<Description> descriptions;

    @Relationship(type = "HAS_SUB_CATEGORIES", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "subCategory")
    @XmlElementWrapper(name = "subCategorys")
    private Set<SubCategory> subCategorys;

    @Relationship(type = "HAS_CLASSIFICATIONS", direction = Relationship.UNDIRECTED)
    @XmlElement(name = "classification")
    @XmlElementWrapper(name = "classifications")
    private Set<Classification> classifications;

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

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void hasComment(Comment comment) {this.comments.add(comment);}

    public void setSubCategorys(Set<SubCategory> subCategorys) {
        this.subCategorys = subCategorys;
    }

    public void hasSubCategory(SubCategory subCategory) { this.subCategorys.add(subCategory);}

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public void hasDescription(Description description) { this.descriptions.add(description);}

    public void setClassifications(Set<Classification> classifications) {
        this.classifications = classifications;
    }

    public void hasClassification(Classification classification) {this.classifications.add(classification);}

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SubCategory)) {
            return false;
        }
        SubCategory subCategory = (SubCategory) o;
        return Objects.equals(target, subCategory.target) &&
                Objects.equals(reference, subCategory.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, reference);
    }

}
