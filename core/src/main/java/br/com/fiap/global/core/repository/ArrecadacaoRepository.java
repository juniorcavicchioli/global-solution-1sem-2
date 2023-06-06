package br.com.fiap.global.core.repository;

import java.util.List;
import java.util.Optional;

import br.com.fiap.global.core.models.Arrecadacao;
import br.com.fiap.global.core.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArrecadacaoRepository extends JpaRepository<Arrecadacao, Long>{

    @Query("SELECT e FROM Arrecadacao e WHERE e.usuario.id = :id")
    public List<Arrecadacao> findByUsuario(Long id);

    @Query("SELECT e FROM Arrecadacao e WHERE e.instituicao.id = :id")
    public List<Arrecadacao> findByInstituicao(Long id);
}
