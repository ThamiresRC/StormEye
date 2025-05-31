package br.com.fiap.stormeye.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.stormeye.config.TokenService;
import br.com.fiap.stormeye.dto.AuthResponseDTO;
import br.com.fiap.stormeye.dto.LoginRequestDTO;
import br.com.fiap.stormeye.exception.BadRequestException;
import br.com.fiap.stormeye.repository.LoginRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginRepository loginRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        var user = loginRepository.findByUsuario(loginDTO.getUsuario())
            .orElseThrow(() -> new BadRequestException("Usuário ou senha inválidos"));

        if (!encoder.matches(loginDTO.getSenha(), user.getSenha())) {
            throw new BadRequestException("Usuário ou senha inválidos");
        }

        String token = tokenService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}
