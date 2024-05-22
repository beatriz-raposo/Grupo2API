package org.serratec.backend.grupo2.dto;

import java.util.Date;
import java.util.HashSet;

import org.serratec.backend.grupo2.model.Usuario;


public class PostagemDTO {
	
	private Long id;
	
	private String conteudo;
	
	private Usuario autor;
	
	private Date dataCriacao;

	public PostagemDTO() {

	}

	public PostagemDTO(Long id, String conteudo, Usuario autor, Date dataCriacao) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.autor = autor;
		this.dataCriacao = dataCriacao;
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
