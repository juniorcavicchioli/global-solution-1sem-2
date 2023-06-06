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

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_arrecadacao")
public class Arrecadacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrecadacao", nullable = false)
    private Long id;

    @Column(name = "vl_arrecadacao", nullable = false)
    private Float valor;
    @Column(name = "dt_arrecadacao", nullable = false)
    private LocalDate dtArrecadacao;
    @Column(name = "tp_pagamento", nullable = false)
    private String tipoPagamento;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_instituicao", nullable = false)
    private Instituicao instituicao;

    public EntityModel<Arrecadacao> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(ArrecadacaoController.class).show(id)).withSelfRel(),
                linkTo(methodOn(ArrecadacaoController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(ArrecadacaoController.class).index(Pageable.unpaged(), null, null)).withRel("all")
        );
    }
}
