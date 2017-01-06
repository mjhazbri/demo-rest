/**
 * 
 */
package fr.blogspot.mjhazbri.utils;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import fr.blogspot.mjhazbri.entities.Customer;
import fr.blogspot.mjhazbri.entities.Invoice;
import fr.blogspot.mjhazbri.services.api.input.CustomerInput;

/**
 * @author jhazbri
 *
 */
@Stateless
@LocalBean
public class Mapper {

	/**
	 * Mapping input --> output
	 * 
	 * @param input
	 * @return {@link Customer}
	 */
	public Customer mapCustomer(CustomerInput input) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		mapperFactory.classMap(CustomerInput.class, Customer.class).byDefault()
				.register();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(input, Customer.class);
	}

	/**
	 * Mapping input --> output
	 * 
	 * @param input
	 * @return list of {@link Invoice}
	 */
	public List<Invoice> mapInvoices(CustomerInput input) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		mapperFactory.classMap(CustomerInput.class, Customer.class).byDefault()
				.register();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.mapAsList(input.getInvoices(), Invoice.class);
	}
}
