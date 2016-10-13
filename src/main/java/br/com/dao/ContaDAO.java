package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pojos.Conta;

public class ContaDAO extends GenericDAO<Integer, Conta>{

	public ContaDAO(EntityManager entity) {
		super(entity);

	}
	
	@SuppressWarnings("unchecked")
	public List<Conta> listarContas(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createQuery("Select CONTA from CONTA as CONTA");
		List<Conta> contas = consulta.getResultList();
		this.em.getTransaction().commit();
		return contas;
	}
	
	public Conta consultaId(Integer id){
		this.em.getTransaction().begin();
		Conta conta = this.getById(id);
		this.em.getTransaction().commit();
		return conta;
	}

}
