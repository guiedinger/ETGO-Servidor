package br.com.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.pojos.Viagem;
import br.com.pojos.Viagem;

public class ViagemDAO extends GenericDAO<Integer, Viagem>{

	
	public ViagemDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	public Viagem criarViagem(Viagem viagem)throws Exception{
		try{
			this.em.getTransaction().begin();
			viagem.setData(new Date());
			viagem = this.save(viagem);
			this.em.getTransaction().commit();
			return viagem;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
