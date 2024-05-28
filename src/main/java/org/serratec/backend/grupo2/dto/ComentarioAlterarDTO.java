package org.serratec.backend.grupo2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComentarioAlterarDTO {
	
	@Id
	@Schema(description = "Identificador unico de comentario")
	private Long id;

	@NotBlank(message = "Texto não pode ser vazio")
	@Size(max = 300, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Texto do comentario")
	private String texto;

	public Long getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
