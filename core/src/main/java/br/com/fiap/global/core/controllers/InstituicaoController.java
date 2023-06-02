package br.com.fiap.global.core.controllers;

import br.com.fiap.global.core.models.Instituicao;
import br.com.fiap.global.core.repository.InstituicaoRepository;
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
@RequestMapping("techbridge/api/instituicao")
@Slf4j
public class InstituicaoController {

    @Autowired
    InstituicaoRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    private Instituicao getInstituicao(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instituicao não encontrada"));
    }
    @PostMapping()
    public ResponseEntity<EntityModel<Instituicao>> signup(@RequestBody @Valid Instituicao instituicao, BindingResult result){
        repository.save(instituicao);
        return ResponseEntity
                .created(instituicao.toEntityModel().getRequiredLink("self").toUri())
                .body(instituicao.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Instituicao> show(@PathVariable Long id){
        var instituicao = getInstituicao(id);
        return instituicao.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 5) Pageable pageable){ //@RequestParam String busca
        Page<Instituicao> instituicaos = repository.findAll(pageable);
        return assembler.toModel(instituicaos.map(Instituicao::toEntityModel));
    }

    @PutMapping("{id}")
    public EntityModel<Instituicao> update(@PathVariable Long id, @RequestBody @Valid Instituicao instituicao, BindingResult result){
        getInstituicao(id);
        instituicao.setId(id);
        repository.save(instituicao);
        return instituicao.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Instituicao> destroy(@PathVariable Long id){
        var instituicaoEncontrada = getInstituicao(id);
        repository.delete(instituicaoEncontrada);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public String login(){
        return "Ainda não implementado";
    }

}
