package org.serratec.backend.grupo2.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.UsuarioAlterarDTO;
import org.serratec.backend.grupo2.dto.UsuarioDTO;
import org.serratec.backend.grupo2.dto.UsuarioInserirDTO;
import org.serratec.backend.grupo2.exception.EmailException;
import org.serratec.backend.grupo2.exception.NotFoundException;
import org.serratec.backend.grupo2.exception.SenhaException;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		 
		 usuarios.forEach(f -> {
			 usuarioDTO.add(adicionarImagemUrl(f));
		 });
		 
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
	
	@Transactional
	public UsuarioDTO alterar(UsuarioAlterarDTO usuario, Usuario usuarioTemp) throws SenhaException {
		Usuario usuarioObt = new Usuario();
		
		usuarioObt.setId(usuario.getId());
		usuarioObt.setDataNasc(usuario.getDataNasc());
		usuarioObt.setEmail(usuario.getEmail());
		usuarioObt.setNome(usuario.getNome());
		usuarioObt.setSobrenome(usuario.getSobrenome());
		usuarioObt.setSenha(encoder.encode(usuario.getSenha()));
		usuarioObt.setPostagens(usuarioTemp.getPostagens());
		usuarioObt.setSeguidores(usuarioTemp.getSeguidores());
		usuarioObt.setSeguindos(usuarioTemp.getSeguindos());
		
		usuarioObt = usuarioRepository.save(usuarioObt);
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioObt);
		return usuarioDTO;
	}
	
    @Autowired
    private ImageService imageService;
 
//    public void uploadProfilePicture(MultipartFile multipartFile, Long userId) throws IOException, NotFoundException {
//        Usuario usuario = findById(userId);
//
//        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
//        jpgImage = imageService.cropSquare(jpgImage);
//        jpgImage = imageService.resize(jpgImage, 200);
//
//        byte[] imageBytes = imageService.convertImageToBytes(jpgImage);
//        usuario.setFoto(imageBytes);
//
//        usuarioRepository.save(usuario);
//    }
    
    public UsuarioDTO adicionarImagemUrl(Usuario usuario) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/usuarios/{id}/foto")
				.buildAndExpand(usuario.getId())
				.toUri();
		
		UsuarioDTO dto = new UsuarioDTO();
		dto.setNome(usuario.getNome());
		dto.setDataNasc(usuario.getDataNasc());
		dto.setSobrenome(usuario.getSobrenome());
		dto.setUrl(uri.toString());	
		return dto;
	}
    
    public UsuarioDTO inserirFoto(Usuario usuario, MultipartFile file) throws IOException {
    	usuario = usuarioRepository.save(usuario);
    	imageService.inserir(usuario, file);
		return adicionarImagemUrl(usuario);
	}
    
    public UsuarioDTO buscar(Long id) {
    	Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
    	return adicionarImagemUrl(usuarioOpt.get());
    }
	}
	
	
	
