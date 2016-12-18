package br.com.dao;

import javax.persistence.EntityManager;

import br.com.pojos.Linha;


public class LinhaDAO extends GenericDAO<Integer, Linha> {

	public LinhaDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	public Linha criarLinha(Linha linha)throws Exception{
		try{
			this.em.getTransaction().begin();
			//linha.setViagens(null);
			linha = this.save(linha);
			this.em.getTransaction().commit();
			return linha;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
