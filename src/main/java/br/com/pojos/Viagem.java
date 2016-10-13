package br.com.pojos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "VIAGEM")
public class Viagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1463607755668231678L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_VIAGEM")
	private Integer idViagem;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_VIAGEM")
	private List<Transacao> transacao;
	
	@ManyToMany
	@JoinTable(name = "VIAGEM_TEM_PASSAGEIROS", 
	joinColumns={@JoinColumn(name = "ID_VIAGEM")}, 
	inverseJoinColumns={@JoinColumn(name = "ID_PASSAGEIRO")})
	private List<Passageiro> passageiros;
	
	@Column(name = "DATA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Integer getIdViagem() {
		return idViagem;
	}



	public void setIdViagem(Integer idViagem) {
		this.idViagem = idViagem;
	}



	public List<Transacao> getTransacao() {
		return transacao;
	}



	public void setTransacao(List<Transacao> transacao) {
		this.transacao = transacao;
	}



	public List<Passageiro> getPassageiros() {
		return passageiros;
	}



	public void setPassageiros(List<Passageiro> passageiros) {
		this.passageiros = passageiros;
	}



	public Viagem(Integer idViagem, List<Passageiro> passageiros) {
		this.idViagem = idViagem;
		this.passageiros = passageiros;
	}



	public Viagem(){
		
	}
}
