package org.serratec.backend.grupo2.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.grupo2.dto.ComentarioDTO;
import org.serratec.backend.grupo2.model.Comentario;
import org.serratec.backend.grupo2.model.Postagem;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.service.ComentarioService;
import org.serratec.backend.grupo2.service.PostagemService;
import org.serratec.backend.grupo2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PostagemService postagemService;

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

	@PostMapping("/{autorId}/{postagemId}")
	public ResponseEntity<ComentarioDTO> inserir(@RequestBody ComentarioDTO comentario)
			throws NotFoundException {
		comentarioService.inserir(comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{autorId}/{postagemId}")
				.buildAndExpand(comentario.getAutorId(),comentario.getPostagemId()).toUri();
		return ResponseEntity.created(uri).body(comentario);
	}

//    @PostMapping("/{id}")
//    public ResponseEntity<Comentario> inserir(@PathVariable Long id, @RequestBody Comentario comentario) {
//        Comentario inserirComentario = comentarioService.inserir(id, comentario);
//        return ResponseEntity.status(HttpStatus.CREATED).body(inserirComentario);
//    }

//    @PostMapping
//    public ResponseEntity<Comentario> criarComentario(@RequestBody Comentario comentario) {
//    	Comentario novoComentario = comentarioService.salvarComentario(comentario);
//        return new ResponseEntity<>(novoComentario, HttpStatus.CREATED);
	// }

//    @PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<ComentarioDTO> inserir(@Valid @RequestBody ComentarioDTO comentariodto, @PathVariable Long id, @PathVariable Long idAutor) {
//    	ComentarioDTO comentarioDTO = comentarioService.inserir(comentariodto, id, idAutor);
//		
//    	 return new ResponseEntity<>(comentarioDTO, HttpStatus.CREATED);
//	}
//    
//    @PostMapping("/postagem/{postagemId}/autor/{autorId}")
//    public ResponseEntity<Comentario> criarComentario(
//            @PathVariable Long postagemId,
//            @PathVariable Long autorId,
//            @RequestBody Comentario comentario) {
//        Comentario novoComentario = comentarioService.salvarComentario(postagemId, autorId, comentario);
//        return new ResponseEntity<>(novoComentario, HttpStatus.CREATED);
//    }

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