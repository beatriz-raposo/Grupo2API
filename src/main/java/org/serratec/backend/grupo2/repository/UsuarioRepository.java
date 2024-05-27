package org.serratec.backend.grupo2.repository;

import java.util.List;

import org.serratec.backend.grupo2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	@Query("SELECT r.id.seguidor FROM Relacionamento r WHERE r.id.seguindo = :seguidor")
	List<Usuario> findSeguidores(Usuario seguidor);

	@Query("SELECT r.id.seguindo FROM Relacionamento r WHERE r.id.seguidor = :seguindo")
	List<Usuario> findSeguindos(Usuario seguindo);
}
