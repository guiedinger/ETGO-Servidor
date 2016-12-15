package br.com.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dao.SimpleEntityManager;
import br.com.dao.TransportadoraDAO;
import br.com.pojos.Transportadora;
import br.com.pojos.Transportadora;

@Path("/transportadora")
public class TransportadoraService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	TransportadoraDAO tDAO = new TransportadoraDAO(sem.getEntityManager());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transportadora> listar(){
		int exceptionNumber = 500;
		try {
			List<Transportadora> transportadoras = tDAO.listarTransportadoras();
			if(transportadoras.isEmpty()){
				exceptionNumber = 404;
				throw new Exception("Nenhuma transportadora cadastrada!");
			}
			return transportadoras;
		} catch (Exception e) {
			throw new WebApplicationException(exceptionNumber);
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Transportadora consultar(@PathParam("id") Integer id){
		int exceptionNumber = 500;
		try{
			Transportadora transportadora = tDAO.consultaId(id);
			if(transportadora == null){
				exceptionNumber = 404;
				throw new Exception("Nenhuma transportadora com esse id!");
			}
			return transportadora;
		} catch (Exception e){
			throw new WebApplicationException(exceptionNumber);
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Transportadora transportadora){
		try{
			transportadora = tDAO.criarTransportadora(transportadora);
			return Response.status(200).entity(transportadora).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response autenticarTransportadora(@FormParam("userName") String userName,
			@FormParam("password") String password){
		try{
			Transportadora Transportadora = tDAO.login(userName, password);	
			return Response.status(200).entity(Transportadora).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
}
