package br.com.ecommerce.api.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.ecommerce.api.model.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
            .withIssuer("api-ecommerce")
            .withSubject(user.getEmail())
            .withExpiresAt(genExpirationDate())
            .sign(algorithm);
        }catch(JWTCreationException ex){
            throw new  RuntimeException("Error while generating token", ex);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
    }
}
