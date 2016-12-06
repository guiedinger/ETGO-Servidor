package br.com.dao;

import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import br.com.pojos.Token;

public class TokenDAO extends GenericDAO<Integer, Token> {

	public TokenDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
    public String buscarToken(String email) throws Exception {
        Query consulta = this.em.createNamedQuery("buscar_token");
        consulta.setParameter("email", email);
        Token t = (Token) consulta.getSingleResult();
        
        if (t.getData().compareTo(new Date()) < 0) {
            t.atualizarToken();
            this.save(t);
        }

        return t.getToken();
    }
    
    public Token validarToken(String token) throws Exception {
        Query consulta = this.em.createNamedQuery("buscar_token_por_conteudo");
        consulta.setParameter("token", token);
        
        Token t = (Token)consulta.getSingleResult();
        
        if (t == null) {
            throw new NotFoundException("Token não encontrado.");
        } else if (t.getData().compareTo(new Date()) < 0) {
            t.atualizarToken();
        } 
        return t;
    }
	
/*	public Token login(String userName, String password) throws Exception{
		Query consulta = this.em.createNamedQuery("autenticar_login");
		consulta.setParameter("userName", userName);
		Token l = (Token) consulta.getSingleResult();
		if(l == null){
			throw new Exception("Login não existe no sistema.");
		}
		if(!password.equals(l.getPassword())){
			System.out.println("Senha incorreta..");
			throw new Exception("Senha incorreta.");
		}
		return l;
	}*/
	
    public Principal getUserPrincipal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
