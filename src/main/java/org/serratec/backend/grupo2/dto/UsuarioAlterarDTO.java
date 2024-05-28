package org.serratec.backend.grupo2.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioAlterarDTO {

	@Id
	@Schema(description = "Identificador unico de usuario")
	private Long id;

	@NotBlank(message = "Preencha o nome do usuario")
	@Size(max = 30, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Nome do usuario")
	private String nome;

	@NotBlank(message = "Preencha o sobrenome do usuario")
	@Size(max = 30, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Sobrenome do usuario")
	private String sobrenome;

	@Email
	@NotBlank(message = "Preencha o email do usuario")
	@Size(max = 40, message = "Tamanho máximo de {max} caracteres")
	@Schema(description = "Email do usuario")
	private String email;

	@NotBlank(message = "Preencha a senha do usuario")
	@Schema(description = "Senha do usuario")
	private String senha;

	@Schema(description = "Data de nascimento do usuario")
	private LocalDate dataNasc;

	public UsuarioAlterarDTO(Long id, String nome, String sobrenome, String email, String senha, LocalDate dataNasc) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

}
