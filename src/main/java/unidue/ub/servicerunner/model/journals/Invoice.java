package unidue.ub.servicerunner.model.journals;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;
import java.util.Objects;

@NodeEntity
public class Invoice {

    @Id
    @GeneratedValue
    private Long Id;

    private Date invoiceDate;

    private Date startDate;

    private Date endDate;

    private String title;

    private String internalId;

    private String file;

    public Invoice() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Invoice)) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return Objects.equals(file, invoice.file) &&
                Objects.equals(title, invoice.title) &&
                Objects.equals(startDate, invoice.startDate) &&
                Objects.equals(invoiceDate, invoice.invoiceDate) &&
                Objects.equals(internalId, invoice.internalId) &&
                Objects.equals(endDate, invoice.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, title, startDate, endDate, internalId, invoiceDate);
    }
}
