package org.serratec.backend.grupo2.dto;

import java.util.Date;

public class ComentarioDTO {
	
	private Long id;
	
	private String texto;
	
	private Date dataCriacao;
	
	private Long idAutor;

	public ComentarioDTO() {
		super();
		
	}

	public ComentarioDTO(Long id, String texto, Date dataCriacao, Long idAutor) {
		super();
		this.id = id;
		this.texto = texto;
		this.dataCriacao = dataCriacao;
		this.idAutor = idAutor;
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

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}
	
	
	
	
}
	
