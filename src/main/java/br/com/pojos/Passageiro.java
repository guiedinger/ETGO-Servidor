package br.com.pojos;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("PASSAGEIRO")
@NamedQueries({
    @NamedQuery(name = "listarPassageiros", query = "select p from Passageiro as p"),
    @NamedQuery(name = "buscarPassageiroPorUserName", query = "SELECT p FROM Passageiro p WHERE LOWER(p.userName) LIKE :userName"),
    @NamedQuery(name = "verificarExistenciaEmailPassageiro", query = "SELECT COUNT(p.email) FROM Passageiro p WHERE p.email LIKE :email")
})
@Table(name = "PASSAGEIRO")
public class Passageiro extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8944075784619545900L;
	
	@Column(name = "CPF", unique = true, nullable = false)
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PASSAGEIRO")
	private List <Avaliacao> avaliacao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PASSAGEIRO")
	private List <Transacao> transacao;
	
	@ManyToMany(mappedBy = "passageiros")
	private List<Viagem> viagens;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoPassageiro tipo;
	
	@Column(name = "TOKEN", nullable = true, unique = true)
	private String token;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Transacao> getTransacao() {
		return transacao;
	}

	public void setTransacao(List<Transacao> transacao) {
		this.transacao = transacao;
	}

	public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}

	public TipoPassageiro getTipo() {
		return tipo;
	}

	public void setTipo(TipoPassageiro tipo) {
		this.tipo = tipo;
	}
	
    public void atualizarToken() {
        Random r = new Random();
        this.token = new BigInteger(130, r).toString(32);
    }
    
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Passageiro(Integer idUsuario, String userName, String password, String nome, String telefone,
			String email, Double saldo, String cpf, List<Avaliacao> avaliacao, List<Transacao> transacao,
			List<Viagem> viagens, TipoPassageiro tipo) {
		super(idUsuario, userName, password, nome, telefone, email, saldo);
		this.cpf = cpf;
		this.avaliacao = avaliacao;
		this.transacao = transacao;
		this.viagens = viagens;
		this.tipo = tipo;
	}

	public Passageiro(){
		
	}
	
}
