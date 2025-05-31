package br.com.fiap.stormeye.service;

import org.springframework.stereotype.Service;

import br.com.fiap.stormeye.exception.ResourceNotFoundException;
import br.com.fiap.stormeye.model.Login;
import br.com.fiap.stormeye.repository.LoginRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository repository;

    public Login buscarPorUsuario(String usuario) {
        return repository.findByUsuario(usuario)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário " + usuario + " não encontrado"));
    }

    public Login buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Login com ID " + id + " não encontrado"));
    }

    public Login salvar(Login login) {
        return repository.save(login);
    }
}
