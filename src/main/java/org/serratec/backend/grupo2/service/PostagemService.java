package org.serratec.backend.grupo2.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.model.Postagem;
import org.serratec.backend.grupo2.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	public List<Postagem> findAll(){
		return postagemRepository.findAll();
	}

    public Optional<Postagem> findById(Long id) {
        return postagemRepository.findById(id);
    }

    public Postagem save(Postagem postagem) {
        postagem.setDataCriacao(new Date());
        return postagemRepository.save(postagem);
    }
    
    public Postagem update(Long id, Postagem postagem) {
        if (postagemRepository.existsById(id)) {
            postagem.setId(id);
            return postagemRepository.save(postagem); 
        } else {
            return null;
        }
    }
    
    public void deleteById(Long id) {
        postagemRepository.deleteById(id);
    }
    

}