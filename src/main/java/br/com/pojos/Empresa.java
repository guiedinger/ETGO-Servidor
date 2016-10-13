package br.com.pojos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("EMPRESA")
@Table(name = "EMPRESA")
public class Empresa extends Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -464061049636228661L;
	
	@Column(name = "CNPJ", unique = true, nullable = false)
	private String cnpj;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA", nullable = true)
	private List <Passageiro> passageiro;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA", nullable = true)
	private List <Transacao> transacao;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	
	public Empresa(Integer idUsuario, String nome, String login, String telefone, String email, Conta conta,
			String cnpj) {
		super(idUsuario, nome, login, telefone, email, conta);
		this.cnpj = cnpj;
	}

	
	
	public Empresa(){
		
	}
}
