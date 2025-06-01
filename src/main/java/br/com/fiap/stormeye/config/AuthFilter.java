package br.com.fiap.stormeye.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.stormeye.repository.LoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final LoginRepository loginRepository;

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    // URLs públicas que não precisam de autenticação
    String path = request.getRequestURI();

    if (path.startsWith("/auth/") || 
        path.startsWith("/v3/api-docs/") || 
        path.startsWith("/swagger-ui") || 
        path.startsWith("/h2-console")) {
        filterChain.doFilter(request, response);
        return;  // Ignora o filtro para rotas públicas
    }

    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);
        String usuario = tokenService.validateToken(token);
        var login = loginRepository.findByUsuario(usuario);
        if (login.isPresent()) {
            var user = login.get();
            var auth = new UsernamePasswordAuthenticationToken(user, null, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    filterChain.doFilter(request, response);
}
}