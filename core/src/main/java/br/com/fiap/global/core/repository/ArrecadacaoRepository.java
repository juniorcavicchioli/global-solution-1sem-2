package br.com.fiap.global.core.repository;

import br.com.fiap.global.core.models.Arrecadacao;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArrecadacaoRepository extends JpaRepository<Arrecadacao, Long>{

    @Query("SELECT e FROM Arrecadacao e WHERE e.usuario.id = :id")
    public Page<Arrecadacao> findByUsuario(Long id);

    @Query("SELECT e FROM Arrecadacao e WHERE e.instituicao.id = :id")
    public Page<Arrecadacao> findByInstituicao(Long id);
}
