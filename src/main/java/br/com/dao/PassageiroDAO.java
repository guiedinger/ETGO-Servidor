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
		Query consulta = this.em.createNamedQuery("listarPassageiros");
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
        	//passageiro.setPassword();
        	passageiro.atualizarToken();
        	passageiro.setSaldo(0.0);
        	
        	this.em.getTransaction().begin();
        	passageiro = this.save(passageiro);
        	this.em.getTransaction().commit();
			
        	return passageiro;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}   
	}
	public Passageiro login(String userName, String password)throws Exception{
		Query consulta = this.em.createNamedQuery("buscarPassageiroPorUserName");
		consulta.setParameter("userName", userName);
		Passageiro p = (Passageiro) consulta.getSingleResult();
		if(p == null){
			throw new Exception("Esse passageiro não existe.");
		}
		if(!password.equals(p.getPassword())){
			throw new Exception("Senha incorreta.");
		}
		return p;
	}
	
    public boolean verificarExistenciaEmail(String email) {
        Query consulta = this.em.createNamedQuery("verificarExistenciaEmailPassageiro");
        consulta.setParameter("email", email);

        try {
            return ((long) consulta.getSingleResult()) == 0;
        } catch (Exception e) {
            throw e;
        }
    }

}
