package org.serratec.backend.grupo2.dto;

import java.util.Date;

import org.serratec.backend.grupo2.model.Comentario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioDTO {

	@NotNull(message = "Postagem não pode ser vazia!")
	@Schema(description = "Postagens relacionadas")
	private Long postagemId;

	@NotNull(message = "Autor não pode ser vazio!")
	@Schema(description = "Autor do comentario")
	private Long autorId;

	@NotBlank(message = "Texto não pode ser vazio")
	@Size(max = 300, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Texto do comentario")
	private String texto;

	@Schema(description = "Data de criação do comentario")
	private Date dataCriacao;

	public ComentarioDTO(Long postagemId, Long autorId, String texto, Date dataCriacao) {
		super();
		this.postagemId = postagemId;
		this.autorId = autorId;
		this.texto = texto;
		this.dataCriacao = dataCriacao;
	}

	public ComentarioDTO(Comentario comentario) {
		this.postagemId = comentario.getpostagem().getId();
		this.autorId = comentario.getAutor().getId();
		this.texto = comentario.getTexto();
		this.dataCriacao = comentario.getDataCriacao();
	}

	public Long getPostagemId() {
		return postagemId;
	}

	public void setPostagemId(Long postagemId) {
		this.postagemId = postagemId;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
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

}
