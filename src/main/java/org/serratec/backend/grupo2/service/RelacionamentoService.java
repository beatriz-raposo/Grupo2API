package org.serratec.backend.grupo2.service;

	import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.model.Relacionamento;
import org.serratec.backend.grupo2.model.RelacionamentoPK;
import org.serratec.backend.grupo2.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	@Service
	public class RelacionamentoService {

	    @Autowired
	    private RelacionamentoRepository relacionamentoRepository;

	    public List<Relacionamento> findAll() {
	        return relacionamentoRepository.findAll();
	    }

	    public Optional<Relacionamento> findById(RelacionamentoPK id) {
	        return relacionamentoRepository.findById(id);
	    }

	    public Relacionamento save(Relacionamento relacionamento) {
	        return relacionamentoRepository.save(relacionamento);
	    }

	    public void deleteById(RelacionamentoPK id) {
	        relacionamentoRepository.deleteById(id);
	    }
	}



