package org.serratec.backend.grupo2.controller;


import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.serratec.backend.grupo2.dto.UsuarioDTO;
import org.serratec.backend.grupo2.dto.UsuarioInserirDTO;
import org.serratec.backend.grupo2.exception.NotFoundException;
import org.serratec.backend.grupo2.model.Foto;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.UsuarioRepository;
import org.serratec.backend.grupo2.service.ImageService;
import org.serratec.backend.grupo2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.ok(usuarioService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO) {
		UsuarioDTO usuarioDTO= usuarioService.inserir(usuarioInserirDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();	
		return ResponseEntity.created(uri).body(usuarioDTO);
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> alterar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		if (!usuarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(id);
		usuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public UsuarioDTO inserirFoto(@RequestPart("file") MultipartFile file, @RequestPart("usuario")  Usuario usuario) 
			throws IOException  {
		return usuarioService.inserirFoto(usuario, file);
	}
    
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
		Foto foto = imageService.buscarPorIdUsuario(id);
		
		HttpHeaders headers = new HttpHeaders(null);
		headers.add("Content-type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		
		return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
	}
	
}
