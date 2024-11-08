package br.com.schoolify.shoolify.controller;

import br.com.schoolify.shoolify.dto.TipoUsuarioDTO;
import br.com.schoolify.shoolify.services.TipoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tipoUsuarios")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoUsuarioDTO> findById(@PathVariable Long id) {
        TipoUsuarioDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<Page<TipoUsuarioDTO>> findAll(Pageable pageable) {
        Page<TipoUsuarioDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<TipoUsuarioDTO> insert(@Valid @RequestBody TipoUsuarioDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TipoUsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody TipoUsuarioDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
