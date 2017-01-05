/**
 * 
 */
package fr.blogspot.mjhazbri.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.blogspot.mjhazbri.entities.Customer;

/**
 * A Data Access Object "DAO" class to manage {@link Customer} entity
 * 
 * @author jhazbri
 *
 */
@Stateless
@LocalBean
public class CustomerDao {

	@PersistenceContext(unitName = "demoPU")
	private EntityManager em;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerDao.class);

	/**
	 * This method is used to create a new {@link Customer}
	 * 
	 * @param customer
	 * @return {@link Customer}
	 */
	public Customer create(Customer customer) {
		LOGGER.debug(".................. customer creation ...............>> begin");
		em.persist(customer);
		LOGGER.debug(".................. customer creation ...............>> end");
		return customer;
	}
	/**
	 * This method is used to update a {@link Customer}
	 * 
	 * @param customer
	 * @return {@link Customer}
	 */
	public Customer update(Customer customer) {
		LOGGER.debug(".................. customer update ...............>> begin");
		em.merge(customer);
		LOGGER.debug(".................. customer update ...............>> end");
		return customer;
	}

	/**
	 * This method is used to find a recorded {@link Customer} by Id
	 * 
	 * @param customerId
	 * @return {@link Customer}
	 */

	public Customer find(String customerId) {
		LOGGER.debug(".................. customer finder ...............>> begin");
		Customer customer = em.find(Customer.class, customerId);
		LOGGER.debug(".................. customer finder ...............>> end");
		return customer;
	}

	/**
	 * This method is used to delete a recorded {@link Customer} by Id
	 * 
	 * @param customerId
	 * @return {@link Customer}
	 */

	public Customer delete(String customerId) {
		LOGGER.debug(".................. customer delete ...............>> begin");
		Customer customer = em.find(Customer.class, customerId);
		em.remove(customer);
		LOGGER.debug(".................. customer delete ...............>> end");
		return customer;
	}

	/**
	 * This method is used to find all recorded {@link Customer}
	 * 
	 * @return list of {@link Customer}
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> find() {
		LOGGER.debug(".................. customer find all ...............>> begin");
		Query query = em.createQuery("SELECT customer FROM Customer customer");
		LOGGER.debug(".................. customer find all ...............>> end");
		return (List<Customer>) query.getResultList();
	}
}
