package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.com.pojos.Passageiro;
import br.com.pojos.Token;

public class PassageiroDAO extends GenericDAO<Integer, Passageiro>{

	public PassageiroDAO(EntityManager entity) {
		super(entity);

	}
	
	@SuppressWarnings("unchecked")
	public List<Passageiro> listarPassageiros(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createQuery("Select p from Passageiro as p");
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
	
	public Passageiro criarPassageiro (Passageiro passageiro) throws Exception {
        try {
        	if (!verificarExistenciaEmail(passageiro.getEmail())) {
        		throw new Exception("Email já existente no sistema.");
        	}
        	Token tk = new Token();
        	//passageiro.setPassword();
        	passageiro.setToken(tk);
        	passageiro.setSaldo(0.0);
        	tk.setUsuario(passageiro);
        	
        	this.em.getTransaction().begin();
        	passageiro = this.save(passageiro);
        	this.em.getTransaction().commit();
			
        	return passageiro;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}   
	}
	
    public boolean verificarExistenciaEmail(String email) {
        Query consulta = this.em.createNamedQuery("verificar_existencia_email_passageiro");
        consulta.setParameter("email", email);

        try {
            return ((long) consulta.getSingleResult()) == 0;
        } catch (Exception e) {
            throw e;
        }
    }

}
