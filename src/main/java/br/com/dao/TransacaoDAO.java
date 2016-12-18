package br.com.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.pojos.TipoTransacao;
import br.com.pojos.Transacao;

public class TransacaoDAO extends GenericDAO<Integer, Transacao>{

	public TransacaoDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	public Transacao criarTransacao(Transacao transacao){
		try{
			this.em.getTransaction().begin();
        	transacao.setData(new Date());
        	if(transacao.getValor()>0){
        		transacao.setTipo(TipoTransacao.RECARGA);
        	}else{
        		transacao.setTipo(TipoTransacao.DEBITO);
        	}
			transacao = this.save(transacao);
			this.em.getTransaction().commit();
			return transacao;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

}
