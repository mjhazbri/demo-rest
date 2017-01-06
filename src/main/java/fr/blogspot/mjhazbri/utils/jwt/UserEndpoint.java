package fr.blogspot.mjhazbri.utils.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserEndpoint {
 
    @Inject
    private KeyGenerator keyGenerator;
	@Context
	private UriInfo uriInfo;
	
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {
        try {
 
            // Authenticate the user using the credentials provided
            authenticate(login, password);
 
            // Issue a token for the user
            String token = issueToken(login);
 
            // Return the token on the response
            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
 
        } catch (Exception e) {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }
 
    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(DateHelper.toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }
    private void authenticate(String login, String password) throws Exception {
//        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_LOGIN_PASSWORD, User.class);
//        query.setParameter("login", login);
//        query.setParameter("password", PasswordUtils.digestPassword(password));
//        User user = query.getSingleResult();
//
//        if (user == null)
//            throw new SecurityException("Invalid user/password");
    }
}