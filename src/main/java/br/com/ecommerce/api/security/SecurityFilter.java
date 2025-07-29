package br.com.ecommerce.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.ecommerce.api.repository.UserRepository;
import br.com.ecommerce.api.service.exceptions.UserNotFoundException;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        var token = request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ") ? request.getHeader("Authorization").substring(7) : request.getHeader("Authorization");
        
        if(token != null && !token.isEmpty()){
            String emailUser;
            emailUser = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(emailUser)
            .orElseThrow(() -> new UserNotFoundException("User authenticated not found"));
            var authenticatedUser = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        }
            filterChain.doFilter(request, response);
    }
    
}
