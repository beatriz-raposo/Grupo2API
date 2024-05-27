package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	@Schema(description = "Identificador unico de comentario")
	private Long id;

	@NotBlank(message = "Texto não pode ser vazio")
	@Size(max = 300, message = "Tamanho máximo de {max} caracteres")
	@Column(nullable = false, length = 300)
	@Schema(description = "Texto do comentario")
	private String texto;

	@Column(name = "data_criacao")
	@Schema(description = "Data de criação do comentario")
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	@JsonBackReference
	@NotNull(message = "Postagem não pode ser vazia!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postagem")
	@Schema(description = "Postagens relacionadas")
	private Postagem postagem;

	@JsonBackReference
	@NotNull(message = "Autor não pode ser vazio!")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	@Schema(description = "Autor do comentario")
	private Usuario autor;

	public Comentario() {

	}

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
