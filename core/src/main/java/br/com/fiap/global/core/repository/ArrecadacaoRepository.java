package br.com.fiap.global.core.repository;

import java.util.List;

import br.com.fiap.global.core.models.Arrecadacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrecadacaoRepository extends JpaRepository<Arrecadacao, Long>{
}
