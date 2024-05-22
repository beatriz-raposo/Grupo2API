package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postagem")
	private Long id;
	
	@Column
	private String conteudo;
	
	@Column
	private Date dataCriacao;
	
	@ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @OneToMany(mappedBy = "postagem")
    private List<Comentario> comentarios;

	public Postagem() {
		
	}

	public Postagem(Long id, String conteudo, Date dataCriacao, Usuario autor, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
		this.autor = autor;
		this.comentarios = comentarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
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
		Postagem other = (Postagem) obj;
		return Objects.equals(id, other.id);
	}
	

}
