package br.com.fiap.global.core.repository;

import br.com.fiap.global.core.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}