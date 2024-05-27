package org.serratec.backend.grupo2.dto;

import java.util.Date;

import org.serratec.backend.grupo2.model.Comentario;

public class ComentarioDTO {
	
	private Long postagemId;
	private Long autorId;
	private String texto;
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
