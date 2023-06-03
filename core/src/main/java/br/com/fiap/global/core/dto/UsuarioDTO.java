package br.com.fiap.global.core.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record UsuarioDTO(String email, String senha) {
    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
