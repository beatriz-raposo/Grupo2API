package org.serratec.backend.grupo2.repository;

import org.serratec.backend.grupo2.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository  extends JpaRepository<Postagem, Long>{

	Postagem findByAutor(String autor);
}
