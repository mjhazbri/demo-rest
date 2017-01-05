/**
 * 
 */
package fr.blogspot.mjhazbri.services.api.input;

import java.util.Set;

/**
 * @author jhazbri
 *
 */
public class CustomerInput {
	private String firstName;
	private String lastName;
	private Set<InvoiceInput> invoices;

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
	public Set<InvoiceInput> getInvoices() {
		return invoices;
	}

	/**
	 * @param invoices
	 *            the invoices to set
	 */
	public void setInvoices(Set<InvoiceInput> invoices) {
		this.invoices = invoices;
	}
}
