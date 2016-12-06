package br.com.ws.rest;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dao.TokenDAO;
import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.pojos.Token;
import br.com.pojos.Passageiro;


@Path("/login")
public class LoginService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	TokenDAO lDAO = new TokenDAO(sem.getEntityManager());
	PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
	
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Token> listar(){
//		int exception = 500;
//		try {
//			List<Token> logins = lDAO.listarLogins();
//			if(logins.isEmpty()){
//				exception = 404;
//				throw new Exception("Nenhum login cadastrado!");
//			}
//			return logins;
//		} catch (Exception e) {
//			throw new WebApplicationException(exception);
//		}
//	}
//	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public Response autenticarLogin(@FormParam("userName") String userName,
//									@FormParam("password") String password){
//		try {
//			Token l = lDAO.login(userName, password);
//			l = lDAO.gerarToken(l);
//
//			
//			sem.getEntityManager().getTransaction().begin();
//			lDAO.save(l);
//			sem.getEntityManager().getTransaction().commit();
//			return Response.ok().encoding("UTF-8").header("Authentication", "Bearer " + l.getToken()).entity(l).build();
//		} catch (Exception ex) {
////			ex.printStackTrace();
//			System.out.println(ex.getMessage());
//            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
//		}
//	}
//	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response cadastrar(Token login, Passageiro passageiro){
//		try {
//			login = lDAO.gerarToken(login);
//
//			sem.getEntityManager().getTransaction().begin();
//			lDAO.create(login);
//			pDAO.create(passageiro);
//			sem.getEntityManager().getTransaction().commit();
//			
//			return	Response.ok(login).header("Authorization", "Bearer "+ login.getToken()).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new WebApplicationException(500);
//		}
//	}
	
	
}
