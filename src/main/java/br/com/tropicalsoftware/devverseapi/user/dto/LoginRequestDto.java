package br.com.tropicalsoftware.devverseapi.user.dto;

public record LoginRequestDto(
        String userName,
        String password
) {
}
