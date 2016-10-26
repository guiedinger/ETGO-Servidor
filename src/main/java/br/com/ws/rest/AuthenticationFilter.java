package br.com.ws.rest;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import br.com.dao.LoginDAO;
import br.com.dao.SimpleEntityManager;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException{
		LoginDAO lDAO = new LoginDAO(sem.getEntityManager());
		
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        requestContext.setSecurityContext(new SecurityContext() {
        	  
        	  @Override
              public Principal getUserPrincipal(){
                  return () -> lDAO.getUserPrincipal().getName();
              }
              
              @Override
              public boolean isUserInRole(String role) {
                  return true;
              }
              
              @Override
              public boolean isSecure() {
                  return false;
              }
              
              @Override
              public String getAuthenticationScheme() {
                  return null;
              }
          });
        	
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Autorização obrigatória.");
        }
        
        String token = authHeader.substring("Bearer".length()).trim();
            
        try {
            lDAO.validarToken(token);
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
        	
	}
		
}


