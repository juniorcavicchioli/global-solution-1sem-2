package br.com.fiap.global.core.repository;

import java.util.List;
import java.util.Optional;

import br.com.fiap.global.core.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("SELECT e FROM Usuario e WHERE e.email = :email")
    public Optional<Usuario> findByEmail(String email);
}
