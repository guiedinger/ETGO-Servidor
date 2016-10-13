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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Linha implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -691387044858908688L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LINHA")
	private Integer idLinha;
	
	@ManyToMany(mappedBy = "linhas")
	private List<Transportadora> transportadora;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_LINHA", nullable = false)
	private List<Viagem> viagens;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@Column(name = "TIPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoServico tipo;
	
	public Integer getIdLinha() {
		return idLinha;
	}
	public void setIdLinha(Integer idLinha) {
		this.idLinha = idLinha;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Linha(Integer idLinha, String descricao) {
		super();
		this.idLinha = idLinha;
		this.descricao = descricao;
	}
	
	public Linha(){
		
	}
}
