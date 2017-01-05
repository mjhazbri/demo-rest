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

import fr.blogspot.mjhazbri.entities.Invoice;

/**
 * A Data Access Object "DAO" class to manage {@link Invoice} entity
 * 
 * @author jhazbri
 *
 */
@Stateless
@LocalBean
public class InvoiceDao {

	@PersistenceContext(unitName = "demoPU")
	private EntityManager em;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(InvoiceDao.class);

	/**
	 * This method is used to create a new {@link Invoice}
	 * 
	 * @param invoice
	 * @return {@link Invoice}
	 */
	public Invoice create(Invoice invoice) {
		LOGGER.debug(".................. invoice creation ...............>> begin");
		em.persist(invoice);
		LOGGER.debug(".................. invoice creation ...............>> end");
		return invoice;
	}

	/**
	 * This method is used to find a recorded {@link Invoice} by Id
	 * 
	 * @param invoiceId
	 * @return {@link Invoice}
	 */

	public Invoice find(String invoiceId) {
		LOGGER.debug(".................. invoice finder ...............>> begin");
		Invoice invoice = em.find(Invoice.class, invoiceId);
		LOGGER.debug(".................. invoice finder ...............>> end");
		return invoice;
	}

	/**
	 * This method is used to delete a recorded {@link Invoice} by Id
	 * 
	 * @param invoiceId
	 * @return {@link Invoice}
	 */

	public Invoice delete(String invoiceId) {
		LOGGER.debug(".................. invoice delete ...............>> begin");
		Invoice invoice = em.find(Invoice.class, invoiceId);
		em.remove(invoice);
		LOGGER.debug(".................. invoice delete ...............>> end");
		return invoice;
	}

	/**
	 * This method is used to find all recorded {@link Invoice}
	 * 
	 * @return list of {@link Invoice}
	 */
	@SuppressWarnings("unchecked")
	public List<Invoice> find() {
		LOGGER.debug(".................. invoice find all ...............>> begin");
		Query query = em.createQuery("SELECT invoice FROM Invoice invoice");
		LOGGER.debug(".................. invoice find all ...............>> end");
		return (List<Invoice>) query.getResultList();
	}
}
