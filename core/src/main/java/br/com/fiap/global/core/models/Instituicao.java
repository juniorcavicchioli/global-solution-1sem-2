package br.com.fiap.global.core.models;

import br.com.fiap.global.core.controllers.InstituicaoController;
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
@Table(name = "t_instituicoes")
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_instituicao", nullable = false)
    private Long id;

    @Column(name = "nm_instituicao", nullable = false)
    private String nome;
    @Column(name = "ds_descricao", nullable = false)
    private String descricao;
    @Column(name = "ds_endereco", nullable = false)
    private String endereco;
    @Column(name = "ds_contato", nullable = false)
    private String contato;

    public EntityModel<Instituicao> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(InstituicaoController.class).show(id)).withSelfRel(),
                linkTo(methodOn(InstituicaoController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(InstituicaoController.class).index(Pageable.unpaged())).withRel("all")
        );
    }
}
