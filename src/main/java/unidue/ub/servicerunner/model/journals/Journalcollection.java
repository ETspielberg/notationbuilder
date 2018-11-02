package unidue.ub.servicerunner.model.journals;

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
	
	private String name;

	private String type;
	
	private int year;

	private Set<Order> orders;

	private Set<Invoice> invoices;

	@Relationship(type = "CONTAINS", direction = Relationship.UNDIRECTED)
	private Set<Journal> journals;
	
	public Journalcollection(){
		name = "";
		identifier = name + String.valueOf(year);
		year = LocalDate.now().getYear();
		journals = new HashSet<>();
		orders = new HashSet<>();
	}

	public Journalcollection(String anchor, int year, String type) {
		this.name = anchor;
		this.year = year;
		this.type = type;
		identifier = anchor + String.valueOf(year);
		journals = new HashSet<>();
		orders = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
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
	
	public int getYear() {
		return year;
	}


	public Journalcollection setName(String name) {
		this.name = name;
		identifier = name + String.valueOf(year);
		return this;
	}
	
	public Journalcollection setYear(int year) {
		this.year = year;
		identifier = name + String.valueOf(year);
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
		clone.setName(name).setJournals(journals).setYear(year);
	    return clone;
	}
	
    public int compareTo(Journalcollection other) {
        if (this.year > other.year)
            return 1;
        else
            return -1;
    }

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Journalcollection)) {
			return false;
		}
		Journalcollection journalcollection = (Journalcollection) o;
		return year == journalcollection.year &&
				Objects.equals(identifier, journalcollection.identifier) &&
				Objects.equals(name, journalcollection.name) &&
				Objects.equals(type, journalcollection.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, identifier, type, year);
	}
}
