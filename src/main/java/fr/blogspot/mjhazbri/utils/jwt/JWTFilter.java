/**
 * 
 */
package fr.blogspot.mjhazbri.utils.jwt;

import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.Key;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jhazbri
 *
 */
@WebFilter(filterName = "JWTFilter", urlPatterns = { "/*" })
public class JWTFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(JWTFilter.class);

	@Inject
	private KeyGenerator keygen;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Get the HTTP Authorization header from the request
		String authorizationHeader = ((HttpServletRequest) request)
				.getHeader(HttpHeaders.AUTHORIZATION);
		logger.info("#### authorization header : " + authorizationHeader);
		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (authorizationHeader == null
				|| !authorizationHeader.startsWith("Bearer ")) {
			logger.error("#### invalid authorizationHeader : "
					+ authorizationHeader);
			((HttpServletResponse) response).sendError(
					HttpServletResponse.SC_UNAUTHORIZED,
					"Authorization header must be provided");
			return;
		}
		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			Key key = keygen.generateKey();
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			logger.info("#### valid token : " + token);

		} catch (Exception e) {
			logger.error("#### invalid token : " + token);
			((HttpServletResponse) response)
					.sendError(HttpServletResponse.SC_UNAUTHORIZED, "invalid token");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
