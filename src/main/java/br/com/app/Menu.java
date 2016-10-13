package br.com.app;

import br.com.dao.ContaDAO;
import br.com.dao.PassageiroDAO;
import br.com.dao.SimpleEntityManager;
import br.com.pojos.Conta;
import br.com.pojos.Passageiro;

public class Menu {

	public static void main(String[] args) {
		
		SimpleEntityManager sem = SimpleEntityManager.getInstance();
		PassageiroDAO pDAO = new PassageiroDAO(sem.getEntityManager());
		ContaDAO cDAO = new ContaDAO(sem.getEntityManager());
		
//		Conta c1 = new Conta(1,23);
//		Passageiro p1 = new Passageiro(0,"Guilhermee","nerdinger","8982393289","guilherme@edinger.com.br",c1,"98213231742");
		Conta c2 = new Conta(2,1000);
		Passageiro p2 = new Passageiro(50,"Guilherme","nerdier","89823989","guilher@edinger.com.br",c2,"9813231742");
		sem.beginTransaction();
//		cDAO.save(c1);
//		pDAO.save(p1);
		cDAO.save(c2);
		pDAO.save(p2);
		sem.commit();
		sem.close();

	}

}
