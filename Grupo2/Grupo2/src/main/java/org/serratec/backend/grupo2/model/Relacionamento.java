package org.serratec.backend.grupo2.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;


@Entity
public class Relacionamento {
	
	@EmbeddedId
    private RelacionamentoPK id;
	
	@Column
	private Date dataInicioSeguimento;

	public Relacionamento() {
	
	}

	public Relacionamento(RelacionamentoPK id, Date dataInicioSeguimento) {
		super();
		this.id = id;
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public RelacionamentoPK getId() {
		return id;
	}

	public void setId(RelacionamentoPK id) {
		this.id = id;
	}

	public Date getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setDataInicioSeguimento(Date dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

}
