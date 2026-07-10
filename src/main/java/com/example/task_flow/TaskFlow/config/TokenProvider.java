package com.example.task_flow.TaskFlow.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.task_flow.TaskFlow.entity.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenProvider {

    @Value("${jwt.key}")
    private String tokenKey;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(User user) {

        Date now = new Date();

        Date expirationTime = new Date(now.getTime() + expiration);

        Algorithm algorithm = Algorithm.HMAC256(tokenKey);

        return JWT.create()
                .withClaim("userId", user.getId())
                .withSubject(user.getEmail())
                .withIssuedAt(Instant.now())
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }

    public Optional<JwtUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenKey);

            DecodedJWT decode = JWT.require(algorithm).build().verify(token);

            return Optional.of(JwtUserData.builder()
                    .id(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .build());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }

    }

}
