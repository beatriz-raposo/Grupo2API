package org.serratec.backend.grupo2.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioInserirDTO {

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

	@NotBlank(message = "Preencha o confirma senha do usuario")
	@Schema(description = "Confirma senha do usuario")
	private String confirmaSenha;
	
	@Schema(description = "Data de nascimento do usuario")
	private LocalDate dataNasc;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

}
