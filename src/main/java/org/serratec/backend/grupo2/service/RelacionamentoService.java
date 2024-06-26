package org.serratec.backend.grupo2.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.RelacionamentoDTO;
import org.serratec.backend.grupo2.model.Relacionamento;
import org.serratec.backend.grupo2.model.RelacionamentoPK;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.RelacionamentoRepository;
import org.serratec.backend.grupo2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class RelacionamentoService {

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<RelacionamentoDTO> findAll() {
		List<Relacionamento> relacionamentos = relacionamentoRepository.findAll();
		List<RelacionamentoDTO> relacionamentoDTO = relacionamentos.stream().map(RelacionamentoDTO::new).toList();
		return relacionamentoDTO;
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

	@Transactional
	public Relacionamento inserir(Usuario seguidor, Usuario seguindo, Date dataInicioSeguimento) {
		RelacionamentoPK pk = new RelacionamentoPK(seguidor, seguindo);
		Relacionamento relacionamento = new Relacionamento(pk, dataInicioSeguimento);
		return relacionamentoRepository.save(relacionamento);
	}

	public List<Usuario> getSeguidor(Long seguidorId) {
		Usuario seguidor = usuarioService.findById(seguidorId);
		return usuarioRepository.findSeguidores(seguidor);
	}

	public List<Usuario> getSeguindo(Long seguindoId) {
		Usuario seguindo = usuarioService.findById(seguindoId);
		return usuarioRepository.findSeguindos(seguindo);
	}
}
