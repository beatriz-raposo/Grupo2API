package org.serratec.backend.grupo2.dto;

import java.util.Date;

import org.serratec.backend.grupo2.model.Postagem;


public class PostagemDTO {
	
	private Long id;
	
	private String conteudo;

	private Date dataCriacao;
	
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
