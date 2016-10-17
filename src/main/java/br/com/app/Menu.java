package br.com.app;


import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.pojos.Passageiro;
import br.com.pojos.TipoPassageiro;

public class Menu {

	public static void main(String[] args) {
		
		SimpleEntityManager sem = SimpleEntityManager.getInstance();
		PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
	
//		Passageiro p1 = new Passageiro(0,"Guilhermee","nerdinger","2468","8982393289","guilherme@edinger.co.br",200.0000,"98213231742",TipoPassageiro.ANTECIPADO);
//
//		sem.beginTransaction();
//		pDAO.save(p1);
//		sem.commit();
//		sem.close();

	}

}
