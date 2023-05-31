package br.com.fiap.global.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_arrecadacao")
public class Arrecadacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_arrecadacao", nullable = false)
    private Long id;

    @Column(name = "vl_arrecadacao", nullable = false)
    private Float valor;
    @Column(name = "dt_arrecadacao", nullable = false)
    private LocalDateTime dtArrecadacao;
    @Column(name = "tp_pagamento", nullable = false)
    private String tipoPagamento;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true) // true é o valor padrão
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_instituicao", nullable = true) // true é o valor padrão
    private Instituicao instituicao;

}
