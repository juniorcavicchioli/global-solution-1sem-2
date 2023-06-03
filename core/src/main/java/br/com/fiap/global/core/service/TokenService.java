package br.com.fiap.global.core.service;

import br.com.fiap.global.core.dto.Token;
import br.com.fiap.global.core.dto.Credencial;
import br.com.fiap.global.core.models.Usuario;
import br.com.fiap.global.core.repository.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;
    private static Algorithm alg = Algorithm.HMAC256("meusecret");
    public Token generateToken(Credencial credencial) {
        var jwt = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("global")
                .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                .sign(alg);
        return new Token(jwt, "JWT", "Bearer");
    }

    public Usuario validate(String token) {
        var email = JWT.require(alg)
                .withIssuer("global")
                .build()
                .verify(token)
                .getSubject();
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new JWTVerificationException("Usuário não encontrado"));
    }
}
