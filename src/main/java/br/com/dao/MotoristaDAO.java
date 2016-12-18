package br.com.dao;

import javax.persistence.EntityManager;

import br.com.pojos.Motorista;

public class MotoristaDAO extends GenericDAO<Integer, Motorista> {

	public MotoristaDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	public Motorista criarMotorista(Motorista motorista)throws Exception{
		try{
			this.em.getTransaction().begin();
			//motorista.setViagem(null);
			motorista = this.save(motorista);
			this.em.getTransaction().commit();
			return motorista;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
