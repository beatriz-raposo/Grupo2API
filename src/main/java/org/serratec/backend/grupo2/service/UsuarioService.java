package src.main.java.org.serratec.backend.grupo2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.config.MailConfig;
import org.serratec.backend.grupo2.dto.UsuarioDTO;
import org.serratec.backend.grupo2.dto.UsuarioInserirDTO;
import org.serratec.backend.grupo2.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import src.main.java.org.serratec.backend.grupo2.exception.SenhaException;
import src.main.java.org.serratec.backend.grupo2.model.Usuario;
import src.main.java.org.serratec.backend.grupo2.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MailConfig mailConfig;
	
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
	

}

