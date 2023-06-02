package br.com.fiap.global.core.repository;

import java.util.List;

import br.com.fiap.global.core.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public List<Usuario> findByEmail(String email);
}
