package org.serratec.backend.grupo2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.UsuarioDTO;
import org.serratec.backend.grupo2.dto.UsuarioInserirDTO;
import org.serratec.backend.grupo2.exception.EmailException;
import org.serratec.backend.grupo2.exception.SenhaException;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
//	
//	@Autowired
//	private MailConfig mailConfig;
	
	//metodo listar tudo
	public List<UsuarioDTO> findAll() {
		 List<Usuario> usuarios = usuarioRepository.findAll();
		 
		 List<UsuarioDTO> usuarioDTO = new ArrayList<>();
		 
		 for (Usuario usuario: usuarios) {
			 UsuarioDTO usuDTO = new UsuarioDTO(usuario);
			 usuarioDTO.add(usuDTO);
		 }
		 return usuarioDTO;
	}
	
	//metodo buscar por id
	public Usuario findById(Long id) throws NotFoundException {
		Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
		if (usuarioOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return usuarioOpt.get();
	}
	
//	//metodo cadastrar usuario
	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException, SenhaException {
		if (!usuarioInserirDTO.getSenha().equalsIgnoreCase(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		Usuario usuarioBd = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (usuarioBd != null) {
			throw new EmailException("Email ja existente");
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setSobrenome(usuarioInserirDTO.getSobrenome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setDataNasc(usuarioInserirDTO.getDataNasc());
		usuario.setSenha(usuarioInserirDTO.getSenha());
			
		usuario = usuarioRepository.save(usuario);
				
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}
	
	
	
	
	
	
	
}
