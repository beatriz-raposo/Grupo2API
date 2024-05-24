package org.serratec.backend.grupo2.dto;

import java.time.LocalDate;

import org.serratec.backend.grupo2.model.Usuario;

public class UsuarioDTO {
	private Long id;
	
	private String nome;
	
	private String sobrenome;

	private String email;
	
	private LocalDate dataNasc;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String sobrenome, String email, String senha, LocalDate dataNasc) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.dataNasc = dataNasc;
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.sobrenome = usuario.getSobrenome();
		this.dataNasc = usuario.getDataNasc();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	 
}

