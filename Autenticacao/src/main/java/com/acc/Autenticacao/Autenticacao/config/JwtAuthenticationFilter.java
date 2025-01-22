package com.acc.Autenticacao.Autenticacao.config;

import com.acc.Autenticacao.Autenticacao.service.CustomUserDetailsService;
import com.acc.Autenticacao.Autenticacao.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@WebFilter("/*")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String AUTH_HEADER = request.getHeader("Authorization");
        final String JWT;
        final String USERNAME;

        if (AUTH_HEADER == null || !AUTH_HEADER.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        JWT = AUTH_HEADER.substring(7); // Remove "Bearer " do token
        USERNAME = jwtService.extractUsername(JWT); // Extrai o username do JWT

        if (USERNAME != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(USERNAME);

            if (jwtService.isTokenValid(JWT, userDetails)) {
                // Cria um token de autenticação
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
