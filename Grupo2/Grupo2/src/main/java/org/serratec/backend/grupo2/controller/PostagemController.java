package org.serratec.backend.grupo2.controller;

import org.serratec.backend.grupo2.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

	
	@Autowired
	private PostagemRepository postagemRepository;
	// criar service antes do controller
}
