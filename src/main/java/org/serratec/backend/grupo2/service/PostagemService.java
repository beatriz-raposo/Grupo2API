package org.serratec.backend.grupo2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.PostagemAlterarDTO;
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

import jakarta.transaction.Transactional;

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

	@Transactional
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

	@Transactional
	public Postagem alterar(PostagemAlterarDTO postagemAlterar, Postagem postagemTemp) {
		Postagem postagemObt = new Postagem();
		
		postagemObt.setId(postagemAlterar.getId());
		postagemObt.setConteudo(postagemAlterar.getConteudo());
		postagemObt.setAutor(postagemTemp.getAutor());
		postagemObt.setDataCriacao(postagemTemp.getDataCriacao());
		postagemObt.setComentarios(postagemTemp.getComentarios());
		
		postagemObt = postagemRepository.save(postagemObt);
		
		Postagem postagem = new Postagem(postagemObt);
		return postagem;
	}

	public void deleteById(Long id) {
		postagemRepository.deleteById(id);
	}

}