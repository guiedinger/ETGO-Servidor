package br.com.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="TIPO_USUARIO")
@Table(name = "USUARIO")
public abstract class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2951580809060761942L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
//	@OneToOne(cascade = CascadeType.ALL, optional = false,
//			fetch = FetchType.EAGER, orphanRemoval = true)
//	@JoinColumn(name = "ID_CONTA")
//	private Conta conta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO", nullable = true)
	private List<Cartao> cartao;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "LOGIN", nullable = false, unique = true)
	private String login;
	
	@Column(name = "PASSWORD", nullable = false, unique = true)
	private String password;
	
	@Column(name = "TELEFONE", nullable = false)
	private String telefone;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name = "SALDO", nullable = false)
	private Double saldo;

	
	
	public List<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public Usuario(Integer idUsuario, String nome, String login, String password, String telefone, String email,
			Double saldo) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.login = login;
		this.password = password;
		this.telefone = telefone;
		this.email = email;
		this.saldo = saldo;
	}

	public Usuario(Integer idUsuario, List<Cartao> cartao, String nome, String login, String password, String telefone,
			String email, Double saldo) {
		super();
		this.idUsuario = idUsuario;
		this.cartao = cartao;
		this.nome = nome;
		this.login = login;
		this.password = password;
		this.telefone = telefone;
		this.email = email;
		this.saldo = saldo;
	}

	public Usuario(){
		
	}
}
