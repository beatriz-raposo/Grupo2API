package org.serratec.backend.grupo2.service;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.ComentarioAlterarDTO;
import org.serratec.backend.grupo2.dto.ComentarioDTO;
import org.serratec.backend.grupo2.exception.NotFoundException;
import org.serratec.backend.grupo2.model.Comentario;
import org.serratec.backend.grupo2.model.Postagem;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.ComentarioRepository;
import org.serratec.backend.grupo2.repository.PostagemRepository;
import org.serratec.backend.grupo2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.transaction.Transactional;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PostagemRepository postagemRepository;

	public List<ComentarioDTO> listar() {
		List<Comentario> comentarios = comentarioRepository.findAll();

		List<ComentarioDTO> comentarioDTO = comentarios.stream().map(ComentarioDTO::new).toList();

		return comentarioDTO;
	}

	public Comentario buscar(@PathVariable Long id) {
		Optional<Comentario> comentarioOpt = comentarioRepository.findById(id);
		if (comentarioOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return comentarioOpt.get();
	}

	@Transactional
	public ComentarioDTO inserir(Usuario autor, Postagem postagem, ComentarioDTO comentariodto)
			throws NotFoundException {
		Optional<Postagem> optionalcomentario = postagemRepository.findById(comentariodto.getPostagemId());
		if (optionalcomentario.isEmpty()) {
			throw new NotFoundException();
		}
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(comentariodto.getAutorId());
		if (usuarioOpt.isEmpty()) {
			throw new NotFoundException();
		}

		Comentario comentario = new Comentario();
		comentario.setAutor(autor);
		comentario.setpostagem(postagem);
		comentario.setTexto(comentariodto.getTexto());
		comentario.setDataCriacao(comentariodto.getDataCriacao());
		comentario = comentarioRepository.save(comentario);

		return new ComentarioDTO(comentario);
	}

	@Transactional
	public Comentario alterar(ComentarioAlterarDTO comentarioAlterar, Comentario comentarioTemp) {
		Comentario comentarioObt = new Comentario();
		
		comentarioObt.setId(comentarioAlterar.getId());
		comentarioObt.setTexto(comentarioAlterar.getTexto());
		comentarioObt.setAutor(comentarioTemp.getAutor());
		comentarioObt.setDataCriacao(comentarioTemp.getDataCriacao());
		comentarioObt.setpostagem(comentarioTemp.getpostagem());
		
		comentarioObt = comentarioRepository.save(comentarioObt);
		
		Comentario comentario = new Comentario(comentarioObt);
		return comentario;
	}
	
	public Comentario delete(Long id) {
		comentarioRepository.deleteById(id);
		return null;
	}

}
