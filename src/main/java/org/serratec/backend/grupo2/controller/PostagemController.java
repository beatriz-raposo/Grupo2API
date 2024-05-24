package org.serratec.backend.grupo2.controller;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<List<Postagem>> listar() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		Optional<Postagem> postagemOpt =  postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(postagemOpt.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Postagem inserir(@Valid @RequestBody Postagem postagem) {
		return  postagemRepository.save(postagem);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Postagem> alterar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		if (! postagemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		postagem.setId(id);
		postagem =  postagemRepository.save(postagem);
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
