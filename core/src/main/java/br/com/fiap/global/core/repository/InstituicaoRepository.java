package br.com.fiap.global.core.repository;

import br.com.fiap.global.core.models.Instituicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

    @Query("SELECT e FROM Instituicao e WHERE e.nome LIKE :nome%")
    Page<Instituicao> findByName(String nome, Pageable pageable);
}