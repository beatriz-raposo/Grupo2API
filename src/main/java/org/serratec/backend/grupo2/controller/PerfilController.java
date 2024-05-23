package org.serratec.backend.grupo2.controller;

import org.serratec.backend.grupo2.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfis")
public class PerfilController {
	
	@Autowired
	PerfilService perfilService;
}
