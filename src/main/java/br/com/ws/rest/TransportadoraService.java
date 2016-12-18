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

import br.com.dao.LinhaDAO;
import br.com.dao.MotoristaDAO;
import br.com.dao.OnibusDAO;
import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.dao.TransportadoraDAO;
import br.com.dao.ViagemDAO;
import br.com.pojos.Linha;
import br.com.pojos.Motorista;
import br.com.pojos.Onibus;
import br.com.pojos.Passageiro;
import br.com.pojos.Transportadora;
import br.com.pojos.Viagem;


@Path("/transportadora")
public class TransportadoraService {

	SimpleEntityManager sem = SimpleEntityManager.getInstance();
	TransportadoraDAO tDAO = new TransportadoraDAO(sem.getEntityManager());
	OnibusDAO oDAO = new OnibusDAO(sem.getEntityManager());
	LinhaDAO lDAO = new LinhaDAO(sem.getEntityManager());
	MotoristaDAO mDAO = new MotoristaDAO(sem.getEntityManager());
	ViagemDAO vDAO = new ViagemDAO(sem.getEntityManager());
	PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
	
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
	@Path("/{id}")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarOnibus/{id}")
	public Response adicionarOnibus(@PathParam("id")Integer id,Onibus onibus){
		try{
			onibus = oDAO.criarOnibus(onibus);
			Transportadora transportadora = tDAO.adicionarOnibus(id, onibus);		
			return Response.status(200).entity(transportadora).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarMotorista/{id}")
	public Response adicionarMotorista(@PathParam("id")Integer id,Motorista motorista){
		try{
			motorista = mDAO.criarMotorista(motorista);
			Transportadora transportadora = tDAO.adicionarMorista(id, motorista);		
			return Response.status(200).entity(transportadora).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/adicionarLinha/{id}")
	public Response adicionarLinha(@PathParam("id")Integer id,Linha linha){
		try{
			linha = lDAO.criarLinha(linha);
			Transportadora transportadora = tDAO.adicionarMorista(id, linha);		
			return Response.status(200).entity(transportadora).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/viagem")
	public Response iniciarViagem(Viagem viagem){
		try{
			viagem = vDAO.criarViagem(viagem);
			return Response.status(200).entity(viagem).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.serverError().entity(e).build();
		}
	}
	
	/*@POST
	@Path("/viagem/adicionarTransacao")
	public Response adicionarTransacao()*/
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/efetuarPagamento")
	public Response efetuarPagamento(@FormParam("id") Integer id, @FormParam("token") String token, @FormParam("valor") Double valor){
		try{
			Passageiro passageiro = pDAO.efetuarPagamento(token, valor);
			Transportadora transportadora = tDAO.efetuarPagamento(id, valor);
			
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
