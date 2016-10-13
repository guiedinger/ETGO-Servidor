package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.com.pojos.Passageiro;

public class PassageiroDAO extends GenericDAO<Integer, Passageiro>{

	public PassageiroDAO(EntityManager entity) {
		super(entity);

	}
	
	@SuppressWarnings("unchecked")
	public List<Passageiro> listarPassageiros(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createQuery("Select passageiro from passageiro");
		List<Passageiro> passageiros = consulta.getResultList();
		this.em.getTransaction().commit();
		return passageiros;
	}
	
	public Passageiro consultaId(Integer id){
		this.em.getTransaction().begin();
		Passageiro passageiro = this.getById(id);
		this.em.getTransaction().commit();
		return passageiro;
	}

}
