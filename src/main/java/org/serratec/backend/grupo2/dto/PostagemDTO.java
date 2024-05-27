package org.serratec.backend.grupo2.dto;

import java.util.Date;

import org.serratec.backend.grupo2.model.Postagem;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostagemDTO {

	@Schema(description = "Identificador unico de postagem")
	private Long id;

	@NotBlank(message = "Conteudo não pode ser vazio")
	@Size(max = 300, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Conteudo da postagem")
	private String conteudo;

	@Schema(description = "Data de criação da postagem")
	private Date dataCriacao;

	@NotNull(message = "Autor não pode ser vazio!")
	@Schema(description = "Autor da postagem")
	private Long idUsuario;

	public PostagemDTO() {

	}

	public PostagemDTO(Long id, String conteudo, Date dataCriacao) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
	}

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
		this.dataCriacao = postagem.getDataCriacao();
		this.idUsuario = postagem.getAutor().getId();
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

}
