package org.serratec.backend.grupo2.service;

import org.serratec.backend.grupo2.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	
}
