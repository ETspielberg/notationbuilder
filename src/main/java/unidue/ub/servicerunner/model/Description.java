package unidue.ub.servicerunner.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NodeEntity
@XmlRootElement(name="description")
public class Description {

    @Id
    @GeneratedValue
    private Long id;

    private String target;

    private String description;

    private List<String> keywords;

    public Description() { }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public void hasKeyword(String keyword) {this.keywords.add(keyword);}
}
