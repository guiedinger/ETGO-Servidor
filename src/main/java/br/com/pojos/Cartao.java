package br.com.pojos;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "CARTAO")
public class Cartao implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2111546054436167022L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CARTAO")
	private Integer idCartao;

	@Column(name = "NUM_CARTAO", nullable = false)
	private String numCartao;

	@Column(name = "COD_SEGURANCA", nullable = false)
	private String codSeguranca;

	@Column(name = "OPERADORA", nullable = false)
	private String operadora;

	public Integer getIdCartao() {
	return idCartao;
	}
	public void setIdCartao(Integer idCartao) {
	this.idCartao = idCartao;
	}
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	public Cartao(Integer idCartao, String numCartao, String codSeguranca, String operadora) {
		super();
		this.idCartao = idCartao;
		this.numCartao = numCartao;
		this.codSeguranca = codSeguranca;
		this.operadora = operadora;
	}
	
	public Cartao(){
		
	}

}
