package br.com.fiap.stormeye.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.fiap.stormeye.model.Administrador;
import br.com.fiap.stormeye.model.Catastrofe;
import br.com.fiap.stormeye.model.Cidade;
import br.com.fiap.stormeye.model.Login;
import br.com.fiap.stormeye.repository.AdministradorRepository;
import br.com.fiap.stormeye.repository.CatastrofeRepository;
import br.com.fiap.stormeye.repository.CidadeRepository;
import br.com.fiap.stormeye.repository.LoginRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final CidadeRepository cidadeRepository;
    private final CatastrofeRepository catastrofeRepository;
    private final LoginRepository loginRepository;
    private final AdministradorRepository administradorRepository;

    @PostConstruct
    public void seed() {
        // Popula cidades
        if (cidadeRepository.count() == 0) {
            cidadeRepository.save(new Cidade(null, "Houston", "EUA"));
            cidadeRepository.save(new Cidade(null, "Daca", "Bangladesh"));
            cidadeRepository.save(new Cidade(null, "Oklahoma City", "EUA"));
            cidadeRepository.save(new Cidade(null, "Denver", "EUA"));
            cidadeRepository.save(new Cidade(null, "Wellington", "Nova Zelândia"));
            cidadeRepository.save(new Cidade(null, "Tóquio", "Japão"));
        }

        // Popula catástrofes com localização
        if (catastrofeRepository.count() == 0) {
            catastrofeRepository.save(new Catastrofe(null, "Tempestade Severa", "Descrição de tempestade severa", 3, "Houston"));
            catastrofeRepository.save(new Catastrofe(null, "Inundação", "Descrição de inundação", 4, "Daca"));
            catastrofeRepository.save(new Catastrofe(null, "Tornado", "Descrição de tornado", 5, "Oklahoma City"));
            catastrofeRepository.save(new Catastrofe(null, "Granizo", "Descrição de granizo", 2, "Denver"));
            catastrofeRepository.save(new Catastrofe(null, "Vento Forte", "Descrição de vento forte", 3, "Wellington"));
            catastrofeRepository.save(new Catastrofe(null, "Terremoto", "Descrição de terremoto", 5, "Tóquio"));
        }

        // Cria login e administrador fixos
        if (loginRepository.count() == 0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            Login login = new Login();
            login.setUsuario("admin");
            login.setSenha(encoder.encode("1234"));
            login.setTipoUsuario("admin");
            loginRepository.save(login);

            Administrador admin = new Administrador();
            admin.setNome("Administrador do Sistema");
            admin.setLogin(login);
            administradorRepository.save(admin);

            System.out.println("✅ Login e administrador padrão criados com sucesso.");
        }
    }
}
