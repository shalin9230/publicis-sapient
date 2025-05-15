package com.assignment.api_gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.List;

@Configuration
public class JwtAuthFilter {
    private static final List<String> PUBLIC_ROUTES = List.of("/api/user/register", "/api/user/login");

    @Value("${jwt.secret}")
    private String secret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isPublicRoute(String path) {
        return PUBLIC_ROUTES.stream().anyMatch((route) -> path.startsWith(route));
    }

    @Bean
    public GlobalFilter globalJwtFilter() {
        return (exchange, chain) -> {
            String requestPath = exchange.getRequest().getURI().getPath();

            // Skip JWT validation for public routes
            if (isPublicRoute(requestPath)) {
                return chain.filter(exchange);
            }

            String token = extractToken(exchange.getRequest().getHeaders());

            if (token == null || !validateToken(token)) {
                return unauthorizedResponse(exchange);
            }

            Claims claims = extractClaims(token);
            exchange = addHeaders(exchange, claims);

            return chain.filter(exchange);
        };
    }

    private String extractToken(HttpHeaders headers) {
        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Invalid or expired token
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private ServerWebExchange addHeaders(ServerWebExchange exchange, Claims claims) {
        String userId = claims.getSubject(); //`sub` in JWT is the user ID
        String role = claims.get("role", String.class);

        return exchange.mutate()
                .request(builder -> builder
                        .header("X-User-Id", userId)
                        .header("X-User-Role", role))
                .build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
