package unidue.ub.servicerunner.model.journals;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Journaltitle implements Cloneable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String issn;
	
	private String type;

	private boolean isActive;

	@Relationship(type = "IS_SAME_JOURNAL", direction = Relationship.UNDIRECTED)
	private Set<Journaltitle> sameJournal;
	
	public Journaltitle() {
		this.name = "";
		this.issn = "";
		this.type="electronic";
		this.isActive = true;
		this.sameJournal = new HashSet<>();
	}

	public Journaltitle(String issn, String type, String name) {
		this.issn = issn;
		this.type = type;
		this.name = name;
		this.isActive = true;
		this.sameJournal = new HashSet<>();
	}
	/**
	 * returns the name of the journal title
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the ISSN of the journal title
	 * @return the issn
	 */
	public String getIssn() {
		return issn;
	}

	/**
	 * returns the type to the journal (print or online)
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * sets the name of the journal title
	 * @param name the name to be set
	 * @return the updated object
	 */
	public Journaltitle setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * sets the ISSN of the journal title
	 * @param issn the issn to be set
	 * @return the updated object
	 */
	public Journaltitle setIssn(String issn) {
		this.issn = issn;
		return this;
	}

	/**
	 * sets the type of the journal title
	 * @param type the type (print or online)
	 * @return the updated object
	 * 	 */
	public Journaltitle setType(String type) {
		this.type = type;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	/**
     * returns a new <code>Journaltitle</code> object as clone of an existing one
     * 
     * @return a clone of the original journal title
     */
    public Journaltitle clone() {
    	Journaltitle clone = new Journaltitle();
    	clone.setIssn(issn).setName(name).setType(type).setActive(isActive);
    	return clone;
    }

    public void isSameJournalAs(Journaltitle journaltitle) {
    	this.sameJournal.add(journaltitle);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Journaltitle)) {
			return false;
		}
		Journaltitle journaltitle = (Journaltitle) o;
		return isActive == journaltitle.isActive &&
				Objects.equals(issn, journaltitle.issn) &&
				Objects.equals(name, journaltitle.name) &&
				Objects.equals(type, journaltitle.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, issn, type, isActive);
	}
}
