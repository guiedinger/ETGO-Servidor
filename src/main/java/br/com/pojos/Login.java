package br.com.pojos;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "LOGIN")
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104224188479864239L;
	
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@JoinColumn(name = "ID_LOGIN")
//	private Integer idLogin;
	@Id
	@Column(name = "USER_NAME", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "TOKEN", nullable = true, unique = true)
	private String token;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true,
			fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;


	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}




	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public Login(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}




	public Login(String userName, String password, String token, Usuario usuario) {
		super();
		this.userName = userName;
		this.password = password;
		this.token = token;
		this.usuario = usuario;
	}




	public Login() {
		
	}
	
	
	
}
