package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Long id;
	
	@Column
	private String texto;
	
	@Column
	private Date dataCriacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_postagem")
    private Postagem postagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Usuario autor;

	

	public Comentario(Long id, String texto, Date dataCriacao, Postagem postagem, Usuario autor) {
		super();
		this.id = id;
		this.texto = texto;
		this.dataCriacao = dataCriacao;
		this.postagem = postagem;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(id, other.id);
	}

	public Postagem getpostagem() {
		return postagem;
	}

	public void setpostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	


}
