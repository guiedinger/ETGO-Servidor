package br.com.pojos;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3943258244486895241L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_AVALIACAO")
	private Integer idAvaliacao;
	
	@Column(name = "NOTA", nullable = false)
	private float nota;
	
	@Column(name = "COMENTARIO", nullable = true)
	private String comentario;
	
	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}
	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	
	public Avaliacao(Integer idAvaliacao, float nota) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.nota = nota;
	}
	
	public Avaliacao(){
	
	}
	
}
