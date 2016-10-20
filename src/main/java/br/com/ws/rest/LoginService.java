package br.com.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dao.LoginDAO;
import br.com.dao.SimpleEntityManager;
import br.com.pojos.Login;


@Path("/login")
public class LoginService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	LoginDAO lDAO = new LoginDAO(sem.getEntityManager());
	
	
	
	
}
