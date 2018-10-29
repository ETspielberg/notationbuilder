package unidue.ub.servicerunner.model.journalHoldings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NodeEntity
public class Journalcollection implements Cloneable, Comparable<Journalcollection> {

	@Id
	@GeneratedValue
	private Long id;

	private String identifier;
	
	private String description;
	
	private String anchor;

	private String type;
	
	private int year;

	@Relationship(type = "HAS_DESCRIPTIONS", direction = Relationship.UNDIRECTED)
	private Set<Journal> journals;
	
	public Journalcollection(){
		description = "";
		anchor = "";
		identifier = anchor + String.valueOf(year);
		year = LocalDate.now().getYear();
		journals = new HashSet<>();
	}

	public Journalcollection(String anchor, int year, String type) {
		this.anchor = anchor;
		this.year = year;
		this.description = "";
		this.type = type;
		identifier = anchor + String.valueOf(year);
		journals = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	public String getIssns() {
		String issns = "";
		Set<Journal> journals = getJournals();
		for (Journal journal : getJournals()) {
			if (!issns.isEmpty())
				issns += ";";
			issns += journal.getIssns();
		}
		return issns;
	}

	public Set<Journal> getJournals() {
		return journals;
	}

	public Journalcollection setJournals(Set<Journal> journals) {
		this.journals = journals;
		return this;
	}

	public void addJournal(Journal journal) {
		this.journals.add(journal);
	}

	public String getDescription() {
		return description;
	}

	public String getAnchor() {
		return anchor;
	}
	
	public int getYear() {
		return year;
	}

	public Journalcollection setDescription(String description) {
		this.description = description;
		return this;
	}

	public Journalcollection setAnchor(String anchor) {
		this.anchor = anchor;
		identifier = anchor + String.valueOf(year);
		return this;
	}
	
	public Journalcollection setYear(int year) {
		this.year = year;
		identifier = anchor + String.valueOf(year);
		return this;
	}

	@JsonIgnore
	public List<String> getIssnsSet() {
		String issns = getIssns();
	    if (issns.contains(";"))
	        issns = issns.replace(";", ",");
		List<String> issnsSet = new ArrayList<>();
	    if (issns.contains(",")){
	        issnsSet = Arrays.asList(issns.split(","));
	    } else
	        issnsSet.add(issns);
	    return issnsSet;
	}
	
	public Journalcollection clone() {
		Journalcollection clone = new Journalcollection();
		clone.setAnchor(anchor).setDescription(description).setJournals(journals).setYear(year);
	    return clone;
	}
	
    public int compareTo(Journalcollection other) {
        if (this.year > other.year)
            return 1;
        else
            return -1;
    }
}
