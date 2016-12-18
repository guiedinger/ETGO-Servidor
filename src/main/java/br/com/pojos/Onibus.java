package br.com.pojos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Onibus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4913321493766909402L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ONIBUS")
	private Integer idOnibus;
	
/*	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = Viagem.class)
	@JoinColumn(name = "ID_ONIBUS", nullable = false)
	private List<Viagem> viagens;*/
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Transportadora transportadora;
	
	@Column(name = "PLACA", nullable = false)
	private String placa;
	
	@Column(name = "MODELO", nullable = false)
	private String modelo;
	
	
/*	public List<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(List<Viagem> viagens) {
		this.viagens = viagens;
	}*/

	public Integer getIdOnibus() {
		return idOnibus;
	}
	
	public void setIdOnibus(Integer idOnibus) {
		this.idOnibus = idOnibus;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Onibus(Integer idOnibus, String placa, String modelo) {
		super();
		this.idOnibus = idOnibus;
		this.placa = placa;
		this.modelo = modelo;
	}
	
	public Onibus(){
		
	}
}
