package br.com.fiap.global.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "nm_isntituicao", nullable = false)
    private String nome;
    @Column(name = "ds_descricao", nullable = false)
    private String descricao;
    @Column(name = "ds_endereco", nullable = false)
    private String endereco;
    @Column(name = "ds_contato", nullable = false)
    private String contato;
}
