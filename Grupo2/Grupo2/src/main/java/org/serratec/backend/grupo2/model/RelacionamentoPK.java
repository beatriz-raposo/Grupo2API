package org.serratec.backend.grupo2.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_seguidor")
	private Usuario seguidor;

	@ManyToOne
	@JoinColumn(name = "id_seguindo")
	private Usuario seguindo;

	public RelacionamentoPK() {
	}

	public RelacionamentoPK(Usuario seguidor, Usuario seguindo) {
		super();
		this.seguidor = seguidor;
		this.seguindo = seguindo;
	}

	public Usuario getSeguidor() {
		return seguidor;
	}

	public void setSeguidor(Usuario seguidor) {
		this.seguidor = seguidor;
	}

	public Usuario getSeguindo() {
		return seguindo;
	}

	public void setSeguindo(Usuario seguindo) {
		this.seguindo = seguindo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
