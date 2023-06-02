package br.com.fiap.global.core.repository;

import br.com.fiap.global.core.models.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}