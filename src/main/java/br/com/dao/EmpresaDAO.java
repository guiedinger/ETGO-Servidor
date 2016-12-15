package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.pojos.Empresa;
import br.com.pojos.Empresa;

public class EmpresaDAO extends GenericDAO<Integer,Empresa> {

	public EmpresaDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Empresa> listarEmpresas(){
		this.em.getTransaction().begin();
		Query consulta = this.em.createNamedQuery("listarEmpresas");
		List<Empresa> Empresas = consulta.getResultList();
		this.em.getTransaction().commit();
		return Empresas;
	}
	
	public Empresa consultaId(Integer id){
		this.em.getTransaction().begin();
		Empresa Empresa = this.getById(id);
		this.em.getTransaction().commit();
		return Empresa;
	}
	
	public Empresa criarEmpresa(Empresa Empresa)throws Exception{
		try{
			if(!verificarExistenciaEmail(Empresa.getEmail())){
				throw new Exception("Email já existente no sistema.");
			}
			Empresa.setSaldo(0.0);
			this.em.getTransaction().begin();
			Empresa = this.save(Empresa);
			this.em.getTransaction().commit();
			
			return Empresa;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Empresa login(String userName, String password)throws Exception{
		Query consulta = this.em.createNamedQuery("buscarEmpresaPorUserName");
		consulta.setParameter("userName", userName);
		Empresa e = (Empresa) consulta.getSingleResult();
		if(e == null){
			throw new Exception("Essa Empresa não existe.");
		}
		if(!password.equals(e.getPassword())){
			throw new Exception("Senha incorreta.");
		}
		return e;
	}
	
    public boolean verificarExistenciaEmail(String email) {
        Query consulta = this.em.createNamedQuery("verificarExistenciaEmailEmpresa");
        consulta.setParameter("email", email);

        try {
            return ((long) consulta.getSingleResult()) == 0;
        } catch (Exception e) {
            throw e;
        }
    }

}
