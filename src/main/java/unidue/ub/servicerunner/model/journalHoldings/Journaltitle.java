package unidue.ub.servicerunner.model.journalHoldings;

import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.*;

@NodeEntity
public class Journaltitle implements Cloneable {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String issn;
	
	private String type;

	private Journal journal;
	
	public Journaltitle() {
		name = "";
		issn = "";
		type="electronic";
	}

	public Journaltitle(String issn, Journal journal, String type, String name) {
		this.issn = issn;
		this.journal = journal;
		this.type = type;
		this.name = name;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public Journal getJournal() {
		return journal;
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

    /**
     * returns a new <code>Journaltitle</code> object as clone of an existing one
     * 
     * @return a clone of the original journal title
     */
    public Journaltitle clone() {
    	Journaltitle clone = new Journaltitle();
    	clone.setIssn(issn).setName(name).setType(type);
    	return clone;
    }


}
