package org.unidue.ub.libintel.adminbackend.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Contract {

    @Id
    @GeneratedValue
    private Long Id;

    private String file;

    private String title;

    private Date contractDate;

    private Date startDate;

    private Date endDate;

    private Conditions conditions;

    @Relationship(type = "HAS_INVOICES")
    private Set<Invoice> invoices;

    @Relationship(type = "WAS_ORDERED_BY")
    private Set<Order> orders;

    @Relationship(type = "GIVES_ACCESS_TO")
    private Set<JournalcollectionAccess> containedJournalcollections;

    @Relationship(type = "GIVES_ACCESS_TO")
    private Set<JournalAccess> containedJournal;

    @Relationship(type = "WITH")
    private Set<Publisher> publishers;

    private Double price;

    private boolean isTest;

    public Contract() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {
        this.publishers = publishers;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Set<JournalcollectionAccess> getContainedJournalcollections() {
        return containedJournalcollections;
    }

    public void setContainedJournalcollections(Set<JournalcollectionAccess> containedJournalcollections) {
        this.containedJournalcollections = containedJournalcollections;
    }

    public Set<JournalAccess> getContainedJournal() {
        return containedJournal;
    }

    public void setContainedJournal(Set<JournalAccess> containedJournal) {
        this.containedJournal = containedJournal;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Contract)) {
            return false;
        }
        Contract contract = (Contract) o;
        return  isTest == contract.isTest &&
                Objects.equals(file, contract.file) &&
                Objects.equals(title, contract.title) &&
                Objects.equals(startDate, contract.startDate) &&
                Objects.equals(price, contract.price) &&
                Objects.equals(endDate, contract.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, title, startDate, endDate, price, isTest);
    }
}
