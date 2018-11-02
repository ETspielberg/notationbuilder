package unidue.ub.servicerunner.model.journals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;
import java.util.Objects;


/**
 * correlates the anchor of Journals to the order number
 * @author Eike Spielberg
 *
 */
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

	@Id
    @GeneratedValue
    private long id;

	private String anchor;

	private String internalId;

	private Date orderDate;

	private String file;

	private String name;

	private String runtime;

	public Order() {
		anchor = "";
		internalId="";
		name="";
		runtime= "";
		file = "";
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * returns the anchor of the collection
	 * @return the anchor
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * sets the anchor of the collection
	 * @param anchor the anchor to set
	 * @return the updated object
	 */
	public Order setAnchor(String anchor) {
		this.anchor = anchor;
		return this;
	}

	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	/**
	 * returns the name of the collection
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the collection
	 * @param name the name to set
	 * @return the updated object
	 */
	public Order setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * returns the time range covered by this collection
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * sets the time range covered by this collection
	 * @param runtime the runtime to set
	 * @return the updated object
	 */
	public Order setRuntime(String runtime) {
		this.runtime = runtime;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Order)) {
			return false;
		}
		Order order = (Order) o;
		return Objects.equals(file, order.file) &&
				Objects.equals(orderDate, order.orderDate) &&
				Objects.equals(anchor, order.anchor) &&
				Objects.equals(internalId, order.internalId) &&
				Objects.equals(runtime, order.runtime) &&
				Objects.equals(name, order.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, orderDate, anchor, internalId, runtime, name);
	}
}
