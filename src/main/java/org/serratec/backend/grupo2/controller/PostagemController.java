package org.serratec.backend.grupo2.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.PostagemAlterarDTO;
import org.serratec.backend.grupo2.dto.PostagemDTO;
import org.serratec.backend.grupo2.model.Postagem;
import org.serratec.backend.grupo2.repository.PostagemRepository;
import org.serratec.backend.grupo2.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private PostagemService postagemService;

	@GetMapping("/paginas")
	public Page<Postagem> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return postagemService.findPaginated(page, size);
	}

	@GetMapping
	public ResponseEntity<List<PostagemDTO>> listar() {
		return ResponseEntity.ok(postagemService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(postagemOpt.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PostagemDTO> inserir(@Valid @RequestBody PostagemDTO postagemDTO) {
		PostagemDTO postagemDTO1 = postagemService.inserir(postagemDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postagemDTO1.getId())
				.toUri();

		return ResponseEntity.created(uri).body(postagemDTO1);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Postagem> alterar(@PathVariable Long id, @Valid @RequestBody PostagemAlterarDTO postagemAlterar) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Postagem postagemTemp = postagemService.findById(id);
		postagemAlterar.setId(id);
		Postagem postagem = postagemService.alterar(postagemAlterar, postagemTemp);
		return ResponseEntity.ok(postagem);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagemRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
