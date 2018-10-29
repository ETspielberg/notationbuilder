package unidue.ub.servicerunner.model.journalHoldings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.util.*;

@NodeEntity
public class Journal implements Cloneable {

	@Id
	@GeneratedValue
	private Long id;

	private String zdbid;
	
	private String actualName;

	private String ezbid;
	
	private String subject;
	
	private String link;
	
	private String publisher;

	@Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
	private Set<Journaltitle> journaltitles;

	private String anchor;

	public Journal() {
		actualName = "";
		ezbid = "";
		zdbid = "";
		link = "";
		subject = "";
		publisher = "";
		journaltitles = new HashSet<>();
	}

	public Journal(String zdbid, String anchor) {
		this.zdbid = zdbid;
		this.anchor = anchor;
		actualName = "";
		ezbid = "";
		link = "";
		subject = "";
		publisher = "";
		journaltitles = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEzbid(String ezbid) {
		this.ezbid = ezbid;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public String getAnchor() {
		return anchor;
	}

	public Set<Journaltitle> getJournaltitles() {
		return journaltitles;
	}

	public Journal setJournaltitles(Set<Journaltitle> journaltitles) {
		this.journaltitles = journaltitles;
		return this;
	}

	public Journal addJournalTitle(Journaltitle journaltitle) {
		journaltitles.add(journaltitle);
		return this;
	}

	public String getZdbid() {
		return zdbid;
	}

	public String getEzbid() {
		return ezbid;
	}

	public Journal setZdbid(String zdbid) {
		this.zdbid = zdbid;
		return this;
	}

	public Journal setEzbID(String ezbID) {
		this.ezbid = ezbID;
		return this;
	}
	
	public Journal setActualName(String actualName) {
		this.actualName = actualName;
		return this;
	}
	
	public Journal setPublisher(String publisher) {
		this.publisher = publisher;
		return this;
	}

	public Journal setLink(String link) {
		this.link = link;
		return this;
	}

	public String getActualName() {
		return actualName;
	}

	public String getSubject() {
		return subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getLink() {
		return link;
	}
	
	public Journal setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	@JsonIgnore
	public String getIssns() {
		String issns = "";
		for (Journaltitle journaltitle : getJournaltitles()) {
			if (!issns.isEmpty())
				issns += ";";
			issns += journaltitle.getIssn();
		}
		return issns;
	}

	@JsonIgnore
	public List<String> getSubjectSet() {
		List<String> subjects = new ArrayList<>();
		if (subject.contains(",")) {
			subjects = Arrays.asList(subject.split(";"));
		} else {
			subjects.add(subject);
		}
		return subjects;
	}

	@JsonIgnore
	public Set<Journaltitle> getElectronicJournals(){
		Set<Journaltitle> electronicJournals = new HashSet<>();
		for (Journaltitle journaltitle : journaltitles) {
			if (journaltitle.getType().equals("electronic"))
				electronicJournals.add(journaltitle);
		}
		return electronicJournals;
	}

	@JsonIgnore
	public Set<Journaltitle> getPrintJournals(){
		Set<Journaltitle> printJournals = new HashSet<>();
		for (Journaltitle journaltitle : journaltitles) {
			if (journaltitle.getType().equals("print"))
				printJournals.add(journaltitle);
		}
		return printJournals;
	}

	public Journal clone() {
		Journal clone = new Journal(zdbid,anchor)
				.setActualName(actualName)
				.setJournaltitles(journaltitles)
				.setLink(link)
				.setPublisher(publisher)
				.setSubject(subject)
				.setEzbID(ezbid);
		return clone;
	}
}
