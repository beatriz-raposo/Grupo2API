package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postagem")
	@Schema(description = "Identificador unico de postagem")
	private Long id;

	@NotBlank(message = "Conteudo não pode ser vazio")
	@Size(max = 300, message = "Tamanho máximo de {max} caracteres")
	@Column(nullable = false, length = 300)
	@Schema(description = "Conteudo da postagem")
	private String conteudo;

	@Column(name = "data_criacao")
	@Schema(description = "Data de criação da postagem")
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	@NotNull(message = "Autor não pode ser vazio!")
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "autor_id")
	@Schema(description = "Autor da postagem")
	private Usuario autor;

	@JsonManagedReference
	@OneToMany(mappedBy = "postagem")
	@Schema(description = "Lista de comentarios relacionados")
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
