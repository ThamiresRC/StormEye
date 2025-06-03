package br.com.fiap.stormeye.controller;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final BCryptPasswordEncoder encoder;


  @PostMapping
    public Login criarLogin(@RequestBody @Valid CadastroLoginDTO loginDTO) {
    Login login = new Login();
    login.setUsuario(loginDTO.getUsuario());
    login.setSenha(encoder.encode(loginDTO.getSenha())); 
    login.setTipoUsuario(loginDTO.getTipoUsuario());
    return repository.save(login); 
}

    @GetMapping
    public List<Login> listarTodos() {
    return repository.findAll();
}


}
