package unidue.ub.servicerunner.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.xml.bind.annotation.XmlRootElement;

@NodeEntity
@XmlRootElement(name="comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private String target;

    public Comment() {}


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
}
