package br.com.dao;

import javax.persistence.EntityManager;

import br.com.pojos.Empresa;

public class EmpresaDAO extends GenericDAO<Integer,Empresa> {

	public EmpresaDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	

}
