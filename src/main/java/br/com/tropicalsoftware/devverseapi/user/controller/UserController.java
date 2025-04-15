package br.com.tropicalsoftware.devverseapi.user.controller;

import br.com.tropicalsoftware.devverseapi.user.domain.User;
import br.com.tropicalsoftware.devverseapi.user.dto.UserRequestDto;
import br.com.tropicalsoftware.devverseapi.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto dto) {
        String userId = userService.createUser(dto);
        return ResponseEntity.created(URI.create("/v1/user/create" + userId)).build();
    }
}
