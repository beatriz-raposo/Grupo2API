package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	@Schema(description = "Identificador unico de usuario")
	private Long id;
	
	@NotBlank(message = "Preencha o nome do usuario")
	@Size(max = 30, message = "Tamanho máximo de {max} caracteres")
	@Column(nullable = false, length = 30)
	@Schema(description = "Nome do usuario")
	private String nome;
	
	@NotBlank(message = "Preencha o sobrenome do usuario")
	@Size(max = 30, message = "Tamanho máximo de {max} caracteres")
	@Column(nullable = false, length = 30)
	@Schema(description = "Sobrenome do usuario")
	private String sobrenome;

	@Email
	@NotBlank(message = "Preencha o email do usuario")
	@Size(max = 40, message = "Tamanho máximo de {max} caracteres")
	@Column(nullable = false, length = 40)
	@Schema(description = "Email do usuario")
	private String email;

	@NotBlank(message = "Preencha a senha do usuario")
	@Column(nullable = false)
	@Schema(description = "Email do usuario")
	private String senha;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	@Schema(description = "Data de nascimento do usuario")
	private Date dataNasc;
	
	@OneToMany(mappedBy = "autor")
	@JsonManagedReference
	@Schema(description = "Postagens relacionadas")
    private List<Postagem> postagens;

    @OneToMany(mappedBy = "id.seguidor")
    @JsonManagedReference
    @Schema(description = "Seguidores relacionadas")
    private List<Relacionamento> seguidores;

    @OneToMany(mappedBy = "id.seguindo")
    @JsonManagedReference
    @Schema(description = "Usuarios seguindo")
    private List<Relacionamento> seguindos;

    
	public Usuario() {
			
	}


	public Usuario(Long id, String nome, String sobrenome, String email, String senha, Date dataNasc,
			List<Postagem> postagens, List<Relacionamento> seguidores, List<Relacionamento> seguindos) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.postagens = postagens;
		this.seguidores = seguidores;
		this.seguindos = seguindos;
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


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Date getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}


	public List<Postagem> getPostagens() {
		return postagens;
	}


	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}


	public List<Relacionamento> getSeguidores() {
		return seguidores;
	}


	public void setSeguidores(List<Relacionamento> seguidores) {
		this.seguidores = seguidores;
	}


	public List<Relacionamento> getSeguindos() {
		return seguindos;
	}


	public void setSeguindos(List<Relacionamento> seguindos) {
		this.seguindos = seguindos;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}


	

	

}
