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

@Entity
@Table(name = "LOGIN")
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104224188479864239L;
	
	@Id
	@Column(name = "LOGIN", nullable = false, unique = true)
	private String login;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "TOKEN", unique = true)
	private String token;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true,
			fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	
}
