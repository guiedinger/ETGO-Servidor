package br.com.pojos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
	@NamedQuery(name = "buscar_token_por_conteudo", query = "select l from Token l where l.token LIKE :token"),
    @NamedQuery(name = "buscar_token_por_nome", query = "SELECT l FROM Token l WHERE LOWER(l.usuario.email) LIKE :email")
})


@Table(name = "TOKEN")
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104224188479864239L;

	@Id
	@Column(name = "ID_TOKEN")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idToken;
	
	@Column(name = "TOKEN", nullable = true, unique = true)
	private String token;
	
	@Column(name = "DATA")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true,fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ID_USUARIO")
	@JsonBackReference
	private Usuario usuario;

	

	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}




	public Integer getIdToken() {
		return idToken;
	}




	public void setIdToken(Integer idToken) {
		this.idToken = idToken;
	}




	public Date getData() {
		return data;
	}




	public void setData(Date data) {
		this.data = data;
	}


    public void atualizarToken() {
        Random r = new Random();
        this.token = new BigInteger(130, r).toString(32);
    }


	public Token() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 30);
        this.data = c.getTime();
        this.atualizarToken();
	}
	
	
	
}
