package br.com.fiap.global.core.controllers;

import br.com.fiap.global.core.models.Log;
import br.com.fiap.global.core.repository.LogRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("techbridge/api/log")
@Slf4j
public class LogController {

    @Autowired
    LogRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    private Log getLog(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Log não encontrada"));
    }
    @PostMapping()
    public ResponseEntity<EntityModel<Log>> signup(@RequestBody @Valid Log log, BindingResult result){
        repository.save(log);
        return ResponseEntity
                .created(log.toEntityModel().getRequiredLink("self").toUri())
                .body(log.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Log> show(@PathVariable Long id){
        var log = getLog(id);
        return log.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 5) Pageable pageable){ //@RequestParam String busca
        Page<Log> logs = repository.findAll(pageable);
        return assembler.toModel(logs.map(Log::toEntityModel));
    }

    @PutMapping("{id}")
    public EntityModel<Log> update(@PathVariable Long id, @RequestBody @Valid Log log, BindingResult result){
        getLog(id);
        log.getUsuario().setId(id);
        repository.save(log);
        return log.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Log> destroy(@PathVariable Long id){
        var logEncontrada = getLog(id);
        repository.delete(logEncontrada);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public String login(){
        return "Ainda não implementado";
    }

}
