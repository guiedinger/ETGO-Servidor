package br.com.pojos;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Achado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3173036631513024574L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ACHADO")
	private Integer idAchado;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true,
			fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ID_VIAGEM")
	private Viagem viagem;
	
	@Column(name = "DESCRICAO", nullable = true)
	private String descricao;
	
	@Column(name = "FOTO", nullable = true)
	private String foto;
	
	@Column(name = "TIPO", nullable = false)
	private TipoAchado tipo;
	
	public Integer getIdAchado() {
		return idAchado;
	}
	public void setIdAchado(Integer idAchado) {
		this.idAchado = idAchado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Achado(Integer idAchado, String descricao) {
		super();
		this.idAchado = idAchado;
		this.descricao = descricao;
	}
	
	public Achado(){
		
	}
}
