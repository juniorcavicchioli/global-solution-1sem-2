package br.com.fiap.global.core.models;

import br.com.fiap.global.core.controllers.ArrecadacaoController;
import br.com.fiap.global.core.controllers.UsuarioController;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long id;

    @Column(name = "nm_usuario", nullable = false)
    private String nome;

    @Column(name = "ds_email", nullable = false)
    private String email;

    @Column(name = "ds_senha", nullable = false)
    private String senha;

    @Column(name = "ds_telefone", nullable = false)
    private String telefone;

    public EntityModel<Usuario> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(UsuarioController.class).show(id)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(UsuarioController.class).index(Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(ArrecadacaoController.class).index(Pageable.unpaged(), id, null)).withRel("arrecadacoes")
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // não existe campo para isso
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // não existe campo para isso
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // não existe campo para isso
    }

    @Override
    public boolean isEnabled() {
        return true; // não existe campo para isso
    }
}
