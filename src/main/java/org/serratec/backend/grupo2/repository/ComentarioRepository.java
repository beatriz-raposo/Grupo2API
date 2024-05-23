package org.serratec.backend.grupo2.repository;

import java.util.List;

import org.serratec.backend.grupo2.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	// Consulta com paginação
	Page<Comentario> findByConteudoContaining(String texto, Pageable pageable);

	// Consulta nativa
	@Query(value = "SELECT * FROM postagem p WHERE p.autor_id = ?1", nativeQuery = true)
	List<Comentario> findByAutorIdNative(Long autorId);

	// Consulta JPQL
	@Query("SELECT p FROM Postagem p WHERE p.autor.id = :autorId")
	List<Comentario> findByAutorIdJPQL(Long autorId);

	// Consulta Spring Data JPA
	List<Comentario> findByAutorId(Long autorId);
}
