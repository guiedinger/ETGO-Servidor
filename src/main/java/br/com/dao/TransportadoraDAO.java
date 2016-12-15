package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pojos.Transportadora;
import br.com.pojos.Transportadora;

public class TransportadoraDAO extends GenericDAO<Integer, Transportadora> {

	public TransportadoraDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Transportadora> listarTransportadoras(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createNamedQuery("listarTransportadoras");
		List<Transportadora> transportadoras = consulta.getResultList();
		this.em.getTransaction().commit();
		return transportadoras;
	}
	
	public Transportadora consultaId(Integer id){
		this.em.getTransaction().begin();
		Transportadora transportadora = this.getById(id);
		this.em.getTransaction().commit();
		return transportadora;
	}
	
	public Transportadora criarTransportadora(Transportadora transportadora)throws Exception{
		try{
			if(!verificarExistenciaEmail(transportadora.getEmail())){
				throw new Exception("Email já existente no sistema.");
			}
			transportadora.setSaldo(0.0);
			this.em.getTransaction().begin();
			transportadora = this.save(transportadora);
			this.em.getTransaction().commit();
			
			return transportadora;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Transportadora login(String userName, String password)throws Exception{
		Query consulta = this.em.createNamedQuery("buscarTransportadoraPorUserName");
		consulta.setParameter("userName", userName);
		Transportadora t = (Transportadora) consulta.getSingleResult();
		if(t == null){
			throw new Exception("Essa Transportadora não existe.");
		}
		if(!password.equals(t.getPassword())){
			throw new Exception("Senha incorreta.");
		}
		return t;
	}
	
    public boolean verificarExistenciaEmail(String email) {
        Query consulta = this.em.createNamedQuery("verificarExistenciaEmailTransportadora");
        consulta.setParameter("email", email);

        try {
            return ((long) consulta.getSingleResult()) == 0;
        } catch (Exception e) {
            throw e;
        }
    }

}
