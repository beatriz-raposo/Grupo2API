package org.serratec.backend.grupo2.repository;

import org.serratec.backend.grupo2.model.Relacionamento;
import org.serratec.backend.grupo2.model.RelacionamentoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, RelacionamentoPK>{
	


}
