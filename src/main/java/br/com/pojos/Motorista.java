package br.com.pojos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "MOTORISTA")
public class Motorista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972801584867490751L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_MOTORISTA")
	private Integer idMotorista;
	
/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Viagem.class)
	@JoinColumn(name = "ID_MOTORISTA", nullable = false)
	private List<Viagem> viagem;*/

//	@ManyToOne(cascade = CascadeType.ALL)
//	private Transportadora transportadora;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "CPF", nullable = false)
	private String cpf;
	
	
	public Integer getIdMotorista() {
		return idMotorista;
	}



	public void setIdMotorista(Integer idMotorista) {
		this.idMotorista = idMotorista;
	}


/*
	public List<Viagem> getViagem() {
		return viagem;
	}



	public void setViagem(List<Viagem> viagem) {
		this.viagem = viagem;
	}*/



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public Motorista(){
		
	}
}
