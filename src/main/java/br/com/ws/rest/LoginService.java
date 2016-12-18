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

import br.com.dao.EmpresaDAO;
import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.dao.TransportadoraDAO;
import br.com.pojos.Empresa;
import br.com.pojos.Passageiro;
import br.com.pojos.Transportadora;


@Path("/login")
public class LoginService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
	TransportadoraDAO tDAO = new TransportadoraDAO(sem.getEntityManager());
	EmpresaDAO eDAO = new EmpresaDAO(sem.getEntityManager());
	

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response autenticarLogin(@FormParam("userName") String userName,
									@FormParam("password") String password){
		String tipo = "Passageiro";
		try {
			Passageiro passageiro = pDAO.login(userName, password);	
			return Response.status(200).entity(passageiro).header("Authorization", tipo ).build();

		} catch (Exception p) {
			try{
				tipo = "Transportadora";
				Transportadora Transportadora = tDAO.login(userName, password);	
				return Response.status(200).entity(Transportadora).header("Authorization", tipo ).build();
			}catch(Exception t) {
				try{
					tipo = "Empresa";
					Empresa Empresa = eDAO.login(userName, password);	
					return Response.status(200).entity(Empresa).header("Authorization", tipo ).build();
				}catch(Exception e){
					e.printStackTrace();
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
			}
		}
	}
	
	
}
