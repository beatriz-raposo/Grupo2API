package org.serratec.backend.grupo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostagemAlterarDTO {

	@Id
	@Schema(description = "Identificador unico de postagem")
	private Long id;
	
	@NotBlank(message = "Conteudo não pode ser vazio")
	@Size(max = 300, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Conteudo da postagem")
	private String conteudo;

	public Long getId() {
		return id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	
}
