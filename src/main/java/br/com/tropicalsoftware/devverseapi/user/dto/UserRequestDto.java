package br.com.tropicalsoftware.devverseapi.user.dto;

public record UserRequestDto(
        String userName,
        String fullName,
        String email,
        String password
) {
}
