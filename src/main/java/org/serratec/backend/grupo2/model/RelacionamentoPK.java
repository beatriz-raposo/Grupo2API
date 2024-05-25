package org.serratec.backend.grupo2.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RelacionamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_seguidor")
	private Usuario seguidor;

	@ManyToOne(fetch = FetchType.EAGER)
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

	@Override
	public int hashCode() {
		return Objects.hash(seguidor, seguindo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelacionamentoPK other = (RelacionamentoPK) obj;
		return Objects.equals(seguidor, other.seguidor) && Objects.equals(seguindo, other.seguindo);
	}

	
}
