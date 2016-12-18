package br.com.dao;

import javax.persistence.EntityManager;

import br.com.pojos.Onibus;

public class OnibusDAO extends GenericDAO<Integer, Onibus> {

	public OnibusDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	public Onibus criarOnibus(Onibus onibus)throws Exception{
		try{
			this.em.getTransaction().begin();
			//onibus.setViagens(null);
			onibus = this.save(onibus);
			this.em.getTransaction().commit();
			return onibus;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
