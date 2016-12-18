package br.com.pojos;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TRANSACAO")
public class Transacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679066782361927002L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTransacao;
	
	@Column(name = "DATA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "VALOR", nullable = false)
	private Double valor;
	
	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipo;
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public TipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	public Integer getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Transacao(Integer idTransacao, Double valor) {
		super();
		this.idTransacao = idTransacao;
		this.valor = valor;
	}
	
	public Transacao(){
		
	}
}
