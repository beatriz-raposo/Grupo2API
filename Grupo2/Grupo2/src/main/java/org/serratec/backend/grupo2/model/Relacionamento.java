package org.serratec.backend.grupo2.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Relacionamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_relacionamento")
	private Long id;
	
	@Column
	private Date dataInicioSeguimento;
	
	@ManyToOne
	@JoinColumn(name = "id_seguidor")
	private Usuario usuarioSeguidor;
	
	@ManyToOne
	@JoinColumn(name = "id_seguindo")
	private Usuario usuarioSeguindo;

	public Relacionamento(Long id, Date dataInicioSeguimento) {
		super();
		this.id = id;
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(Date dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
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
		Relacionamento other = (Relacionamento) obj;
		return Objects.equals(id, other.id);
	}
	
	


}
