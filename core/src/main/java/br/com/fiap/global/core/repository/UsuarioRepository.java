package br.com.fiap.global.core.repository;

import java.util.List;
import java.util.Optional;

import br.com.fiap.global.core.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Optional<Usuario> findByEmail(String email);
}
