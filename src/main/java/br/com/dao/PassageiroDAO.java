package br.com.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import br.com.pojos.Passageiro;
import br.com.pojos.TipoTransacao;
import br.com.pojos.Transacao;


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
	
	public Passageiro atualizarSaldo(Integer idPassageiro, Double credito) throws Exception {
        try {
        	Passageiro passageiro = this.getById(idPassageiro);
        	passageiro.setSaldo(passageiro.getSaldo()+credito);
        	/*Transacao transacao = null;
        	transacao.setValor(credito);
        	transacao.setData(new Date());
        	if(credito>0){
        		transacao.setTipo(TipoTransacao.RECARGA);
        	}else{
        		transacao.setTipo(TipoTransacao.DEBITO);
        	}
        	List<Transacao> transacoes = passageiro.getTransacao();
        	transacoes.add(transacao);
        	passageiro.setTransacao(transacoes);
     */
        	this.em.getTransaction().begin();
        	passageiro = this.save(passageiro);
        	this.em.getTransaction().commit();
			
        	return passageiro;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}   
	}
	public Passageiro efetuarPagamento(String token, Double valor)throws Exception{
		try{
			Query consulta = this.em.createNamedQuery("buscarPassageiroPorToken");
			consulta.setParameter("token", token);
			Passageiro passageiro = (Passageiro) consulta.getSingleResult();
			if((passageiro.getSaldo()-valor)>=0){
			passageiro.setSaldo(passageiro.getSaldo()-valor);
			}else{
				throw new Exception("Saldo insuficiente");
			}
			this.em.getTransaction().begin();
			passageiro = this.save(passageiro);
			this.em.getTransaction().commit();
			
			return passageiro;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Passageiro atualizarTokenPassageiro (Integer idPassageiro) throws Exception {
        try {
        	Passageiro passageiro = this.getById(idPassageiro);
        	passageiro.atualizarToken();

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
