package br.com.dao;

import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import br.com.pojos.Login;

public class LoginDAO extends GenericDAO<Integer, Login> {

	public LoginDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public List<Login> listarLogins(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createNamedQuery("listar_todos_logins");
		List<Login> logins = consulta.getResultList();
		this.em.getTransaction().commit();
		return logins;
	}
	
	public Login login(String userName, String password) throws Exception{
		Query consulta = this.em.createNamedQuery("autenticar_login");
		consulta.setParameter("userName", userName);
		Login l = (Login) consulta.getSingleResult();
		if(l == null){
			throw new Exception("Login não existe no sistema.");
		}
		if(!password.equals(l.getPassword())){
			System.out.println("Senha incorreta..");
			throw new Exception("Senha incorreta.");
		}
		return l;
	}
	
	public Login validarToken(String token) throws Exception{
		Query consulta = this.em.createNamedQuery("buscar_token_por_conteudo");
		consulta.setParameter("token", token);
		Login l = (Login)consulta.getSingleResult();
		if(l == null){
			throw new NotFoundException("Token não encontrado.");
		}
		return l;
	}
	
	public Login gerarToken(Login login) throws Exception{
		Random random = new SecureRandom();
		login.setToken(new BigInteger(130, random).toString(32));
		return login;
	}
	
	
	public Login consultaId(Integer id){
		this.em.getTransaction().begin();
		Login login = this.getById(id);
		this.em.getTransaction().commit();
		return login;
	}
	
	 public Principal getUserPrincipal() {
	        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	    }
	
}
