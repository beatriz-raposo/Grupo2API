package org.serratec.backend.grupo2.service;

import java.io.IOException;
import java.util.Optional;

import org.serratec.backend.grupo2.model.Foto;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Service
public class ImageService {

	@Autowired
	private FotoRepository fotoRepository;

	public Foto inserir(Usuario usuario, MultipartFile file) throws IOException {
		Foto foto = new Foto();
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto.setUsuario(usuario);

		foto = fotoRepository.save(foto);

		return foto;
	}

	@Transactional
	public Foto buscarPorIdUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);

		Optional<Foto> fotoOpt = fotoRepository.findByUsuario(usuario);

		if (fotoOpt.isEmpty()) {
			return null;
		}
		return fotoOpt.get();
	}

}
