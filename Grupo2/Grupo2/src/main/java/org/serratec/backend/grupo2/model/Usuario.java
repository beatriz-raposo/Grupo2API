package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String sobrenome;

	@Column
	private String email;

	@Column
	private String senha;
	
	@Column
	private Date dataNasc;
	
	@OneToMany(mappedBy = "autor")
    private List<Postagem> postagens;

    @OneToMany(mappedBy = "seguidor")
    private List<Relacionamento> seguidores;

    @OneToMany(mappedBy = "seguindo")
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
