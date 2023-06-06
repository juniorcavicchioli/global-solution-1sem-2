package br.com.fiap.global.core.controllers;

import br.com.fiap.global.core.models.Arrecadacao;
import br.com.fiap.global.core.repository.ArrecadacaoRepository;
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
@RequestMapping("api/arrecadacao")
@Slf4j
public class ArrecadacaoController {

    @Autowired
    ArrecadacaoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    private Arrecadacao getArrecadacao(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Arrecadacao não encontrada"));
    }
    @PostMapping()
    public ResponseEntity<EntityModel<Arrecadacao>> create(@RequestBody @Valid Arrecadacao arrecadacao, BindingResult result){
        repository.save(arrecadacao);
        return ResponseEntity
                .created(arrecadacao.toEntityModel().getRequiredLink("self").toUri())
                .body(arrecadacao.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Arrecadacao> show(@PathVariable Long id){
        var arrecadacao = getArrecadacao(id);
        return arrecadacao.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 5) Pageable pageable, @RequestParam Long usuario, @RequestParam Long instituicao){
        Page<Arrecadacao> arrecadacaos;
        if (usuario != null && instituicao == null) {
            arrecadacaos = (instituicao == null) ?
                    repository.findByUsuario(usuario) :
                    repository.findByInstituicao(instituicao);
        } else {
            arrecadacaos = repository.findAll(pageable);
        }
        return assembler.toModel(arrecadacaos.map(Arrecadacao::toEntityModel));
    }

    @PutMapping("{id}")
    public EntityModel<Arrecadacao> update(@PathVariable Long id, @RequestBody @Valid Arrecadacao arrecadacao, BindingResult result){
        getArrecadacao(id);
        arrecadacao.setId(id);
        repository.save(arrecadacao);
        return arrecadacao.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Arrecadacao> destroy(@PathVariable Long id){
        var arrecadacaoEncontrada = getArrecadacao(id);
        repository.delete(arrecadacaoEncontrada);
        return ResponseEntity.noContent().build();
    }

}
