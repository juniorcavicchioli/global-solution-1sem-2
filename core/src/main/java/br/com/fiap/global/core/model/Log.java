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
@Table(name = "t_logs")
public class Log {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false) // true é o valor padrão
    private Usuario usuario;

    @Column(name = "dt_ocorrencia", nullable = false)
    private LocalDateTime dataOcorrencia;
    @Column(name = "cd_erro", nullable = false)
    private Integer erro;
    @Column(name = "ds_mensagem", nullable = false)
    private String mensagem;
}
