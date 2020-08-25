package org.unidue.ub.libintel.adminbackend.model.systematic;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@NodeEntity
@XmlRootElement(name="description")
public class Description {

    @Id
    @GeneratedValue
    private Long id;

    private String target;

    private String text;

    private Set<String> keywords;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void hasKeyword(String keyword) {this.keywords.add(keyword);}
}
