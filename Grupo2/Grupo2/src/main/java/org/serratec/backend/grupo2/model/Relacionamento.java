package org.serratec.backend.grupo2.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;


@Entity
public class Relacionamento {
	
	@EmbeddedId
    private RelacionamentoPK id = new RelacionamentoPK();
	
	@Column
	private Date dataInicioSeguimento;

	public Relacionamento() {
	
	}

	public Relacionamento(Usuario seguidor, Usuario seguindo, Date dataInicioSeguimento) {
		super();
		this.id.setSeguidor(seguidor);
		this.id.setSeguindo(seguindo);
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
