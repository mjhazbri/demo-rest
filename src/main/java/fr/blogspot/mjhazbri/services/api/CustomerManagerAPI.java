/**
 * 
 */
package fr.blogspot.mjhazbri.services.api;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.blogspot.mjhazbri.entities.Customer;
import fr.blogspot.mjhazbri.services.CustomerManager;
import fr.blogspot.mjhazbri.services.api.input.CustomerInput;
import fr.blogspot.mjhazbri.tools.Mapper;

/**
 * @author jhazbri
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/customer/")
public class CustomerManagerAPI {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomerManagerAPI.class);

	@Context
	private UriInfo uriInfo;
	@Inject
	private CustomerManager customerManager;
	@Inject
	private Mapper mapper;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(CustomerInput customerInput) {
		LOGGER.debug("addCustomer .. ");
		Customer customer = customerManager.confirm(
				mapper.mapCustomer(customerInput),
				mapper.mapInvoices(customerInput));
		URI location = uriInfo.getRequestUriBuilder()
				.path("/" + customer.getId()).build();
		return Response.accepted().location(location).build();
	}
}
