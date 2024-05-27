package org.serratec.backend.grupo2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.PostagemDTO;
import org.serratec.backend.grupo2.exception.NotFoundException;
import org.serratec.backend.grupo2.model.Postagem;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.repository.PostagemRepository;
import org.serratec.backend.grupo2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Postagem> findPaginated(int page, int size) {
		return postagemRepository.findAll(PageRequest.of(page, size));
	}

	public List<PostagemDTO> findAll() {
		List<Postagem> postagens = postagemRepository.findAll();

		List<PostagemDTO> postagemDTO = new ArrayList<>();

		for (Postagem postagem : postagens) {
			PostagemDTO posDTO = new PostagemDTO(postagem);
			postagemDTO.add(posDTO);
		}
		return postagemDTO;
	}

	public Postagem findById(Long id) throws NotFoundException {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isEmpty()) {
			throw new NotFoundException();
		}
		return postagemOpt.get();
	}

	public PostagemDTO inserir(PostagemDTO postagemdto) throws NotFoundException {
		Optional<Usuario> byId = usuarioRepository.findById(postagemdto.getIdUsuario());
		if (byId.isEmpty()) {
			throw new NotFoundException();
		}
		Postagem postagem = new Postagem();
		postagem.setConteudo(postagemdto.getConteudo());
		postagem.setDataCriacao(postagemdto.getDataCriacao());
		postagem.setAutor(byId.get());
		postagem = postagemRepository.save(postagem);
		PostagemDTO postagemDTO = new PostagemDTO(postagem);
		return postagemDTO;
	}

	public PostagemDTO update(Long id, PostagemDTO postagemdto) {
		Optional<Postagem> postagemOpt = postagemRepository.findById(id);
		if (postagemOpt.isPresent()) {
			Postagem postagem = postagemOpt.get();
			postagem.setConteudo(postagemdto.getConteudo());
			postagem = postagemRepository.save(postagem);
			return new PostagemDTO(postagem);
		} else {
			throw new NotFoundException();
		}
	}

	public void deleteById(Long id) {
		postagemRepository.deleteById(id);
	}

}