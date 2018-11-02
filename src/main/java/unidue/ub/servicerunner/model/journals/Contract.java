package unidue.ub.servicerunner.model.journals;

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

    private Date startDate;

    private Date endDate;

    private Conditions conditions;

    @Relationship(type = "HAS_INVOICES")
    private Set<Invoice> invoices;

    @Relationship(type = "HAS_ORDERS")
    private Set<Order> orders;

    private Double price;

    private boolean isTest;

    public Contract() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
