package fr.blogspot.mjhazbri.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * This class is used to represents the Customer entity
 * 
 * @author jhazbri
 *
 */
@Entity
public class Customer {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private Set<Invoice> invoices = new HashSet<>();

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the invoices
	 */
	public Set<Invoice> getInvoices() {
		return invoices;
	}

	/**
	 * @param invoices
	 *            the invoices to set
	 */
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}