package br.com.pojos;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("PASSAGEIRO")
@Table(name = "PASSAGEIRO")
public class Passageiro extends Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8944075784619545900L;
	
	@Column(name = "CPF", unique = true, nullable = false)
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PASSAGEIRO")
	private List <Avaliacao> avaliacao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PASSAGEIRO")
	private List <Transacao> transacao;
	
	@ManyToMany(mappedBy = "passageiros")
	private List<Viagem> viagens;

	@Column(name = "TIPO")
	@Enumerated(EnumType.STRING)
	private TipoPassageiro tipo;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Passageiro(Integer idUsuario, String nome,String login, String telefone, String email, Conta conta,
			String cpf) {
		super(idUsuario, nome, login, telefone, email, conta);
		this.cpf = cpf;
	}

	public Passageiro(){
		
	}
	
}
