package br.com.pojos;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Conta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587342564666145130L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTA")
	private Integer idConta;
	
	@Column(name = "SALDO")
	private Integer saldo;
	
	public Integer getIdConta() {
		return idConta;
	}
	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	public Integer getSaldo() {
		return saldo;
	}
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	public Conta(Integer idConta, Integer saldo) {
		super();
		this.idConta = idConta;
		this.saldo = saldo;
	}
	
	public Conta(){
		
	}
	
}
