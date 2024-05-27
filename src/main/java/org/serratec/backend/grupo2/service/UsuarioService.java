package org.serratec.backend.grupo2.service;

import java.awt.image.BufferedImage;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

//	@Autowired
//	private MailConfig mailConfig;
	
	//metodo listar tudo
	public List<UsuarioDTO> findAll() {
		 List<Usuario> usuarios = usuarioRepository.findAll();
		 
		 List<UsuarioDTO> usuarioDTO = usuarios.stream().map(UsuarioDTO::new).toList();

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
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));
		
		usuario = usuarioRepository.save(usuario);
				
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}
	
    @Autowired
    private ImageService imageService;

 
    public void uploadProfilePicture(MultipartFile multipartFile, Long userId) throws IOException, NotFoundException, java.io.IOException {
        Usuario usuario = findById(userId);

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, 200);

        byte[] imageBytes = imageService.convertImageToBytes(jpgImage);
        usuario.setFoto(imageBytes);

        usuarioRepository.save(usuario);
    }
    
	}
	
	
	
