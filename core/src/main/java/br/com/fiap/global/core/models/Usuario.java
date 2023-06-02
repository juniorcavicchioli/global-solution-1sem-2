package br.com.fiap.global.core.models;

import br.com.fiap.global.core.controllers.UsuarioController;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
                linkTo(methodOn(UsuarioController.class).index(Pageable.unpaged())).withRel("all")
        );
    }
}
