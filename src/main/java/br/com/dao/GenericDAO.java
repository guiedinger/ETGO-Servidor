package br.com.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO<PK, T> {
	
	EntityManager em;
	
	public GenericDAO(EntityManager entityManager) {
        this.em = entityManager;
    }
	
	@SuppressWarnings("unchecked")
	public T getById(PK pk) {
		return (T) em.find(getTypeClass(), pk);
	}

	public void create(T entity) {
		em.persist(entity);
	}

	public T save(T entity) {
		return em.merge(entity);
	}

	public void delete(T entity) {
		em.remove(entity);
	}
	
	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}
	
}
