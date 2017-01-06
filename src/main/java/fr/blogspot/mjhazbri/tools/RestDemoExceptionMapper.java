package fr.blogspot.mjhazbri.tools;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class RestDemoExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RestDemoExceptionMapper.class);
	@Override
	public Response toResponse(Exception e) {
		LOGGER.error("Exception thrown : detail ", e);
		if (e instanceof ClientErrorException) {
			return Response.status(Status.BAD_REQUEST)
					.entity(Error.build("CLIENT_ERROR", e.getMessage()))
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		} else if (e instanceof WebApplicationException) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(Error.build("SERVER_ERROR", "Server error"))
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(Error.build("SERVER_ERROR", "Server error"))
				.type(MediaType.APPLICATION_JSON_TYPE).build();
	}

}