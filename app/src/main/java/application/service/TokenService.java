package application.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import application.model.Usuario;

@Service
public class TokenService {
    
    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("teste");
            return JWT.create()
            .withIssuer("API Quizzes")
            .withSubject(usuario.getNomeDeUsuario())
            .withExpiresAt(expirationDate())
            .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar JWT");
        }
    } 

    private Instant expirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}