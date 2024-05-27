package org.serratec.backend.grupo2.repository;

import java.util.Optional;

import org.serratec.backend.grupo2.model.Foto;
import org.serratec.backend.grupo2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long>{
	
	public Optional<Foto> findByUsuario(Usuario usuario);

}
