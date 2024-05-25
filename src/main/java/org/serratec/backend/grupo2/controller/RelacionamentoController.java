package org.serratec.backend.grupo2.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.grupo2.dto.RelacionamentoDTO;
import org.serratec.backend.grupo2.model.Relacionamento;
import org.serratec.backend.grupo2.model.RelacionamentoPK;
import org.serratec.backend.grupo2.model.Usuario;
import org.serratec.backend.grupo2.service.RelacionamentoService;
import org.serratec.backend.grupo2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {

    @Autowired
    private RelacionamentoService relacionamentoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<RelacionamentoDTO>> getAllRelacionamentos() {
        return ResponseEntity.ok(relacionamentoService.findAll());
    }

    @GetMapping("/{seguidorId}/{seguindoId}")
    public ResponseEntity<Relacionamento> getRelacionamento(@PathVariable Long seguidorId, @PathVariable Long seguindoId) throws NotFoundException {
    	Usuario seguidor = usuarioService.findById(seguidorId);
    	Usuario seguindo = usuarioService.findById(seguindoId);
    	Optional<Relacionamento> relacionamento = relacionamentoService.findById(new RelacionamentoPK(seguidor, seguindo));
        if (relacionamento.isPresent()) {
            return new ResponseEntity<>(relacionamento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<RelacionamentoDTO> createRelacionamento(@RequestBody RelacionamentoDTO relacionamento) throws NotFoundException {
		Usuario seguidor = usuarioService.findById(relacionamento.getSeguidorId());
		Usuario seguindo = usuarioService.findById(relacionamento.getSeguindoId());
        relacionamentoService.inserir(seguidor, seguindo, relacionamento.getDataInicioSeguimento());
        return ResponseEntity.ok(relacionamento);
    }

    @PutMapping("/{seguidorId}/{seguindoId}")
    public ResponseEntity<Relacionamento> updateRelacionamento(@PathVariable Long seguidorId, @PathVariable Long seguindoId, @RequestBody Relacionamento relacionamentoDetails) throws NotFoundException {
    	Usuario seguidor = usuarioService.findById(seguidorId);
    	Usuario seguindo = usuarioService.findById(seguindoId);
    	Optional<Relacionamento> relacionamento = relacionamentoService.findById(new RelacionamentoPK(seguidor, seguindo));
        if (relacionamento.isPresent()) {
            Relacionamento existingRelacionamento = relacionamento.get();
            existingRelacionamento.setDataInicioSeguimento(relacionamentoDetails.getDataInicioSeguimento());
            Relacionamento updatedRelacionamento = relacionamentoService.save(existingRelacionamento);
            return new ResponseEntity<>(updatedRelacionamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{seguidorId}/{seguindoId}")
    public ResponseEntity<Void> deleteRelacionamento(@PathVariable Long seguidorId, @PathVariable Long seguindoId) throws NotFoundException {
    	Usuario seguidor = usuarioService.findById(seguidorId);
    	Usuario seguindo = usuarioService.findById(seguindoId);
        Optional<Relacionamento> relacionamento = relacionamentoService.findById(new RelacionamentoPK(seguidor, seguindo));
        if (relacionamento.isPresent()) {
            relacionamentoService.deleteById(new RelacionamentoPK(seguidor, seguindo));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
