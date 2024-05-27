package org.serratec.backend.grupo2.service;

import java.util.List;
import java.util.Optional;

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

//    public Comentario inserir(@PathVariable Long id, Comentario comentario) throws NotFoundException {
//    	 Optional<comentario> optionalcomentario = comentarioRepository.findById(id);
//    	 if(optionalcomentario.isEmpty()) {
//    		 throw new NotFoundException();	
//    	 }
//        return comentarioRepository.save(comentario);
//    }

//	public Comentario salvarComentario(Comentario comentario) throws NotFoundException{
//		Long comentarioId = comentario.getcomentario().getId();
//		comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(() -> new NotFoundException());
//
//		Long autorId = comentario.getAutor().getId();
//		Usuario autor = usuarioRepository.findById(autorId).orElseThrow(() -> new NotFoundException());
//
//		comentario.setcomentario(comentario);
//		comentario.setAutor(autor);
//		return comentarioRepository.save(comentario);
//	}
	
	 public ComentarioDTO inserir(ComentarioDTO comentariodto) throws NotFoundException {
		 Optional<Postagem> optionalcomentario = postagemRepository.findById(comentariodto.getPostagemId());
	    	if (optionalcomentario.isEmpty()) {
	    		throw new NotFoundException();
	    	}
	    	Optional<Usuario> usuarioOpt = usuarioRepository.findById(comentariodto.getAutorId());
	        if (usuarioOpt.isEmpty()) {
	        	throw new NotFoundException();
	        }
	
			Comentario comentario = new Comentario();
	    	comentario.setTexto(comentariodto.getTexto());
	    	comentario.setDataCriacao(comentariodto.getDataCriacao());
	        comentario = comentarioRepository.save(comentario);
	       
	        return new ComentarioDTO(comentario);
	    }

	public Comentario atualizar(@PathVariable Long id, Comentario novoComentario) {
		Optional<Comentario> optionalComentario = comentarioRepository.findById(id);
		if (optionalComentario.isPresent()) {
			novoComentario.setId(id);
			return (Comentario) comentarioRepository.save(novoComentario);
		} else {

			return null;
		}
	}

	public Comentario delete(Long id) {
		comentarioRepository.deleteById(id);
		return null;
	}

}
