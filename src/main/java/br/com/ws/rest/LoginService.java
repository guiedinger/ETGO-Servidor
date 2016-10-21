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
	
	
	@GET
	@Produces
	public List<Login> listar(){
		int exception = 500;
		try {
			List<Login> logins = lDAO.listarLogins();
			if(logins.isEmpty()){
				exception = 404;
				throw new Exception("Nenhum login cadastrado!");
			}
			return logins;
		} catch (Exception e) {
			throw new WebApplicationException(exception);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Login login){
		try {
			sem.getEntityManager().getTransaction().begin();
			lDAO.create(login);
			sem.getEntityManager().getTransaction().commit();
			return Response.status(200).entity(login).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	
}
