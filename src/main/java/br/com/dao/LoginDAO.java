package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pojos.Login;

public class LoginDAO extends GenericDAO<Integer, Login> {

	public LoginDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public List<Login> listarLogins(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createQuery("select l from Login as l");
		List<Login> logins = consulta.getResultList();
		this.em.getTransaction().commit();
		return logins;
	}
	
	public Login consultaId(Integer id){
		this.em.getTransaction().begin();
		Login login = this.getById(id);
		this.em.getTransaction().commit();
		return login;
	}

	
}
