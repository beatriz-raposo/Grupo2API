package org.serratec.backend.grupo2.dto;

import java.util.Date;

import org.serratec.backend.grupo2.model.Relacionamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RelacionamentoDTO {

	@NotNull(message = "Id do seguidor não pode ser vazio!")
	private Long seguidorId;

	@NotNull(message = "Id do seguindo não pode ser vazio!")
	private Long seguindoId;

	@NotBlank(message = "Data do seguimento não pode ser vazio!")
	@Schema(description = "Data inicial de seguimento")
	private Date dataInicioSeguimento;

	public RelacionamentoDTO(Long seguidorId, Long seguindoId, Date dataInicioSeguimento) {
		super();
		this.seguidorId = seguidorId;
		this.seguindoId = seguindoId;
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

	public RelacionamentoDTO(Relacionamento relacionamento) {
		this.seguidorId = relacionamento.getId().getSeguidor().getId();
		this.seguindoId = relacionamento.getId().getSeguindo().getId();
		this.dataInicioSeguimento = relacionamento.getDataInicioSeguimento();
	}

	public Long getSeguidorId() {
		return seguidorId;
	}

	public Long getSeguindoId() {
		return seguindoId;
	}

	public Date getDataInicioSeguimento() {
		return dataInicioSeguimento;
	}

	public void setSeguidorId(Long seguidorId) {
		this.seguidorId = seguidorId;
	}

	public void setSeguindoId(Long seguindoId) {
		this.seguindoId = seguindoId;
	}

	public void setDataInicioSeguimento(Date dataInicioSeguimento) {
		this.dataInicioSeguimento = dataInicioSeguimento;
	}

}
