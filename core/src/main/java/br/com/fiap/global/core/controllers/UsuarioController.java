package br.com.fiap.global.core.controllers;

import br.com.fiap.global.core.dto.Token;
import br.com.fiap.global.core.dto.Credencial;
import br.com.fiap.global.core.models.Usuario;
import br.com.fiap.global.core.repository.UsuarioRepository;
import br.com.fiap.global.core.service.TokenService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuario")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @Autowired
    TokenService tokenService;

    private Usuario getUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrada"));
    }
    @PostMapping("/registrar")
    public ResponseEntity<EntityModel<Usuario>> signup(@RequestBody @Valid Usuario usuario, BindingResult result){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity
                .created(usuario.toEntityModel().getRequiredLink("self").toUri())
                .body(usuario.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Usuario> show(@PathVariable Long id){
        var usuario = getUsuario(id);
        return usuario.toEntityModel();
    }

    @GetMapping()
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 5) Pageable pageable){ //@RequestParam String busca
        Page<Usuario> usuarios = repository.findAll(pageable);
        return assembler.toModel(usuarios.map(Usuario::toEntityModel));
    }

    @PutMapping("{id}")
    public EntityModel<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario, BindingResult result){
        getUsuario(id);
        usuario.setId(id);
        repository.save(usuario);
        return usuario.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        var usuarioEncontrada = getUsuario(id);
        repository.delete(usuarioEncontrada);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("login")
    public ResponseEntity<Token> login(@RequestBody Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

}
