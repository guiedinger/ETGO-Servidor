 package br.com.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.pojos.Passageiro;

@Path("/passageiro")
public class PassageiroService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Passageiro consultar(@PathParam("id") Integer idPassageiro){
		int exceptionNumber = 500;
		try{
			Passageiro passageiro = pDAO.consultaId(idPassageiro);
			if(passageiro == null){
				exceptionNumber = 404;
				throw new Exception("Nenhum passageiro com esse id!");
			}
			return passageiro;
		} catch (Exception e){
			throw new WebApplicationException(exceptionNumber);
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/atualizarSaldo")
	public Response atualizarSaldo(@FormParam("id") Integer idPassageiro,@FormParam("credito") Double credito){
		int exceptionNumber = 500;
		try{
			Passageiro passageiro = pDAO.atualizarSaldo(idPassageiro, credito);
			if(passageiro == null){
				exceptionNumber = 404;
				throw new Exception("Nenhum passageiro com esse id!");
			}
			return Response.status(200).entity(passageiro).build();
		} catch (Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualizarToken/{id}")
	public Response atualizarToken(@PathParam("id") Integer idPassageiro){
		int exceptionNumber = 500;
		try{
			Passageiro passageiro = pDAO.atualizarTokenPassageiro(idPassageiro);
			if(passageiro == null){
				exceptionNumber = 404;
				throw new Exception("Nenhum passageiro com esse id!");
			}
			return Response.status(200).entity(passageiro).build();
		} catch (Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Passageiro> listar(){
		int exceptionNumber = 500;
		try {
			List<Passageiro> passageiros = pDAO.listarPassageiros();
			if(passageiros.isEmpty()){
				exceptionNumber = 404;
				throw new Exception("Nenhum passageiro cadastrado!");
			}
			return passageiros;
		} catch (Exception e) {
			throw new WebApplicationException(exceptionNumber);
		}
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(Passageiro passageiro) throws Exception{
		try {
			passageiro = pDAO.criarPassageiro(passageiro);
			return Response.status(200).entity(passageiro).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response autenticarPassageiro(@FormParam("userName") String userName,
			@FormParam("password") String password){
		try{
			Passageiro passageiro = pDAO.login(userName, password);	
			return Response.status(200).entity(passageiro).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
}

