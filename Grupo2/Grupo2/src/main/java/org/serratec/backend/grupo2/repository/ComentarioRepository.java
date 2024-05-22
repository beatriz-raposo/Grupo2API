package org.serratec.backend.grupo2.repository;

import org.serratec.backend.grupo2.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository  extends JpaRepository<Comentario, Long>{

	Comentario findByAutor(String autor);
	
	Comentario findByPostagens(String postagens);
}
