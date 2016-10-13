package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SimpleEntityManager {
	private static SimpleEntityManager instance;
    private EntityManagerFactory emf;
    private EntityManager em;
    
//    public SimpleEntityManager(EntityManagerFactory factory) {
//        this.emf = factory;
//        this.em = factory.createEntityManager();
//    }
//     
//    public SimpleEntityManager(String persistenceUnitName){
//        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
//        this.em = emf.createEntityManager();
//    }
    
    private SimpleEntityManager(){
    	if (instance == null) {
    		emf = Persistence.createEntityManagerFactory("ETGO");
    		this.em = emf.createEntityManager();
    	}
    }
    
    public static synchronized SimpleEntityManager getInstance(){
    	if(instance == null){
    		instance = new SimpleEntityManager();
    	}
    	return instance;
    }
 
    public void beginTransaction(){
        em.getTransaction().begin();
    }
     
    public void commit(){
        em.getTransaction().commit();
    }
     
    public void close(){
        em.close();
        emf.close();
    }
     
    public void rollback(){
        em.getTransaction().rollback();
    }
     
    public EntityManager getEntityManager(){
        return this.em;
    }
}
