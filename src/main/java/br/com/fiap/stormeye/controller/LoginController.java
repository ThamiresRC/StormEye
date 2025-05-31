package br.com.fiap.stormeye.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.stormeye.dto.CadastroLoginDTO;
import br.com.fiap.stormeye.model.Login;
import br.com.fiap.stormeye.repository.LoginRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/logins")
@RequiredArgsConstructor
public class LoginController {

    private final LoginRepository repository;

    @PostMapping
    public String criarLogin(@RequestBody @Valid CadastroLoginDTO loginDTO) {
        Login login = new Login();
        login.setUsuario(loginDTO.getUsuario());
        login.setSenha(loginDTO.getSenha());
        login.setTipoUsuario(loginDTO.getTipoUsuario());
        repository.save(login);

        return "Login criado com sucesso!";
    }
}
