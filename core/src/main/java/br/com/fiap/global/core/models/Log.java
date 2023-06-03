package br.com.fiap.global.core.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_logs")
public class Log {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "dt_ocorrencia", nullable = false)
    private LocalDateTime dataOcorrencia;
    @Column(name = "cd_erro", nullable = false)
    private Integer erro;
    @Column(name = "ds_mensagem", nullable = false)
    private String mensagem;

//    public EntityModel<Log> toEntityModel() {
//        return EntityModel.of(
//                this,
//                linkTo(methodOn(LogController.class).show(usuario.getId())).withSelfRel(),
//                linkTo(methodOn(LogController.class).destroy(usuario.getId())).withRel("delete"),
//                linkTo(methodOn(LogController.class).index(Pageable.unpaged())).withRel("all")
//        );
//    }
}
