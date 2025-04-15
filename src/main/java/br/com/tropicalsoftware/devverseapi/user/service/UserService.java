package br.com.tropicalsoftware.devverseapi.user.service;

import br.com.tropicalsoftware.devverseapi.user.domain.User;
import br.com.tropicalsoftware.devverseapi.user.dto.LoginRequestDto;
import br.com.tropicalsoftware.devverseapi.user.dto.UserRequestDto;
import br.com.tropicalsoftware.devverseapi.user.repository.UserRepository;
import br.com.tropicalsoftware.devverseapi.util.JwtToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository repository;
    private JwtToken jwtToken;

    public UserService(UserRepository repository, JwtToken jwtToken) {
        this.repository = repository;
        this.jwtToken = jwtToken;
    }

    public String createUser(UserRequestDto dto) {

        var entity = new User(
                UUID.randomUUID(),
                dto.userName(),
                dto.fullName(),
                dto.email(),
                dto.password()
        );

        try {
            UUID useId = repository.save(entity).getUserId();
            return useId.toString();
        }catch (Exception e) {
            throw new RuntimeException(e + " - " + e.getMessage());
        }
    }

    public String loginUser(LoginRequestDto dto) {
        try{
            var user = repository.findByUserName(dto.userName()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            if(!user.getPassword().equals(dto.password())) {
                throw new RuntimeException("Senha incorreta");
            }

            return jwtToken.gereteToken(user.getUserName());
        }
        catch (Exception e) {
            throw new RuntimeException(e + " - " + e.getMessage());
        }
    }
}
