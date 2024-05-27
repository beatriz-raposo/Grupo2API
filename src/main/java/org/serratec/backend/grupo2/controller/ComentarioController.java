package org.serratec.backend.grupo2.controller;

import java.util.List;

import org.serratec.backend.grupo2.dto.ComentarioDTO;
import org.serratec.backend.grupo2.exception.SeguidorException;
import org.serratec.backend.grupo2.model.Comentario;
import org.serratec.backend.grupo2.model.Postagem;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.service.ComentarioService;
import org.serratec.backend.grupo2.service.PostagemService;
import org.serratec.backend.grupo2.service.RelacionamentoService;
import org.serratec.backend.grupo2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PostagemService postagemService;

	@Autowired
	private RelacionamentoService relacionamentoService;

	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> listar() {
		return ResponseEntity.ok(comentarioService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comentario> buscar(@PathVariable Long id) {
		Comentario comentarioPorId = comentarioService.buscar(id);
		if (comentarioPorId != null) {
			return ResponseEntity.ok().body(comentarioPorId);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<ComentarioDTO> inserir(@Valid @RequestBody ComentarioDTO comentario)
			throws SeguidorException {
		Usuario autor = usuarioService.findById(comentario.getAutorId());
		Postagem postagem = postagemService.findById(comentario.getPostagemId());
		usuarioService.findById(comentario.getAutorId());
		List<Usuario> seguidores = relacionamentoService.getSeguidor(postagem.getAutor().getId());
		for (Usuario seguidor : seguidores) {
			if (comentario.getAutorId().equals(seguidor.getId())) {
				comentarioService.inserir(autor, postagem, comentario);
				return ResponseEntity.ok(comentario);
			} else {
				throw new SeguidorException("Não está seguindo o autor!");
			}
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @RequestBody Comentario comentario) {
		Comentario atualizarComentario = comentarioService.atualizar(id, comentario);
		return ResponseEntity.ok().body(atualizarComentario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Comentario> deletar(@PathVariable Long id) {
		Comentario deletarComentario = comentarioService.delete(id);
		return ResponseEntity.ok().body(deletarComentario);
	}

}