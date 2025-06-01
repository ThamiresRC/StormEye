package br.com.fiap.stormeye.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class JwtConfigTest {

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @PostConstruct
    public void init() {
        System.out.println("===== JWT EXPIRATION =====");
        System.out.println("jwt.expiration = " + jwtExpiration);
        System.out.println("==========================");
    }
}
