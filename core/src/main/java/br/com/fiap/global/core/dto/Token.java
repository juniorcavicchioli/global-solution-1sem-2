package br.com.fiap.global.core.dto;

public record Token(
        String token,
        String type,
        String prefix
) {
}
