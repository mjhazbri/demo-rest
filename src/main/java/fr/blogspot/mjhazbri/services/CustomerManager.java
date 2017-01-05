/**
 * 
 */
package fr.blogspot.mjhazbri.services;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.blogspot.mjhazbri.dao.CustomerDao;
import fr.blogspot.mjhazbri.entities.Customer;
import fr.blogspot.mjhazbri.entities.Invoice;

/**
 * This class is used to manage all operation for customer
 * 
 * @author jhazbri
 *
 */
@ApplicationScoped
public class CustomerManager {

	@Inject
	private CustomerDao customerDao;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerManager.class);

	/**
	 * This method is used to confirm the buy action
	 * 
	 * @param customer
	 * @param invoices
	 */
	@SuppressWarnings("unchecked")
	public Customer confirm(Customer customer, List<Invoice> invoices) {
		LOGGER.debug(".................. confirmation ...............>> begin");
		customer.setInvoices((Set<Invoice>) invoices);
		if (customer.getId() == null) {
			LOGGER.debug(".................. confirmation ...............>> add customer because does not exist ..");
			customer = customerDao.create(customer);
		} else {
			customer = customerDao.create(customer);
		}
		LOGGER.debug(".................. confirmation ...............>> begin");
		return customer;
	}
}
