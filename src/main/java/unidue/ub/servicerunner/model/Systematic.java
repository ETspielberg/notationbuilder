package unidue.ub.servicerunner.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NodeEntity
@XmlRootElement(name="systematic")
public class Systematic {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    @Relationship(type = "HAS_SUBJECT_AREAS", direction = Relationship.DIRECTION)
    @XmlElement(name = "subjectAreas")
    private List<SubjectArea> subjectAreas;

    public Systematic() {}

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

    public void setSubjectAreas(List<SubjectArea> subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    public void hasSubjectArea(SubjectArea subjectArea) {this.subjectAreas.add(subjectArea);}
}
