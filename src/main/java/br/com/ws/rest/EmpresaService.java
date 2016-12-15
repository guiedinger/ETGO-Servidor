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
import br.com.dao.EmpresaDAO;
import br.com.pojos.Empresa;
import br.com.pojos.Empresa;

@Path("/empresa")
public class EmpresaService {
	
	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	EmpresaDAO eDAO = new EmpresaDAO(sem.getEntityManager());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empresa> listar(){
		int exceptionNumber = 500;
		try {
			List<Empresa> Empresas = eDAO.listarEmpresas();
			if(Empresas.isEmpty()){
				exceptionNumber = 404;
				throw new Exception("Nenhuma Empresa cadastrada!");
			}
			return Empresas;
		} catch (Exception e) {
			throw new WebApplicationException(exceptionNumber);
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
	public Empresa consultar(@PathParam("id") Integer id){
		int exceptionNumber = 500;
		try{
			Empresa Empresa = eDAO.consultaId(id);
			if(Empresa == null){
				exceptionNumber = 404;
				throw new Exception("Nenhuma Empresa com esse id!");
			}
			return Empresa;
		} catch (Exception e){
			throw new WebApplicationException(exceptionNumber);
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Empresa Empresa){
		try{
			Empresa = eDAO.criarEmpresa(Empresa);
			return Response.status(200).entity(Empresa).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response autenticarEmpresa(@FormParam("userName") String userName,
			@FormParam("password") String password){
		try{
			Empresa Empresa = eDAO.login(userName, password);	
			return Response.status(200).entity(Empresa).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
}
