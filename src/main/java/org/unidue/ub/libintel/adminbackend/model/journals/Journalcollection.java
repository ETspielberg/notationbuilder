package org.unidue.ub.libintel.adminbackend.model.journals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.util.*;

@NodeEntity
public class Journalcollection implements Cloneable {

	@Id
	@GeneratedValue
	private Long id;

	private String identifier;
	
	private String name;

	private String type;
	
	private Date startDate;

	private Date endDate;

	@Relationship(type = "CONTAINS", direction = Relationship.UNDIRECTED)
	private Set<Journal> journals;
	
	public Journalcollection(){
		name = "";
		identifier = "";
		journals = new HashSet<>();
	}

	public Journalcollection(String identifier){
		name = "";
		this.identifier = identifier;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void addJournals(Set<Journal> journals) {
		this.journals.addAll(journals);
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

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Journalcollection)) {
			return false;
		}
		Journalcollection journalcollection = (Journalcollection) o;
		return Objects.equals(startDate, journalcollection.startDate) &&
				Objects.equals(endDate, journalcollection.endDate) &&
				Objects.equals(identifier, journalcollection.identifier) &&
				Objects.equals(name, journalcollection.name) &&
				Objects.equals(type, journalcollection.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, identifier, type, startDate, endDate);
	}
}
