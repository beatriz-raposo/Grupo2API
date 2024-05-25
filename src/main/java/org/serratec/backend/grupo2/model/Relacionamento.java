package org.serratec.backend.grupo2.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "relacionamento")
public class Relacionamento {
	
	@EmbeddedId
	@JsonBackReference
    private RelacionamentoPK id;
	
	@Column(name = "data_inicio_seguimento")
	@Temporal(TemporalType.DATE)
	@Schema(description = "Data inicial de seguimento")
	private Date dataInicioSeguimento;

	public Relacionamento() {
	
	}

	public Relacionamento(RelacionamentoPK id, Date dataInicioSeguimento) {
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

	public void setSeguidor(Usuario seguidor) {
		this.id.setSeguidor(seguidor);
	}
	
	public void setSeguindo(Usuario seguindo) {
		this.id.setSeguidor(seguindo);
	}

}
