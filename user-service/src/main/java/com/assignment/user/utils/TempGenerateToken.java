package com.assignment.user.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TempGenerateToken {



    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("admin@123");
        log.info("password ::"+password);
/*        Map<String, Object> claims = new HashMap<>();

        claims.put("role", "guest");
        claims.put("date", LocalDateTime.now().toString());
        claims.put("message", "some other message");
        byte[] keyBytes = Decoders.BASE64.decode("9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9");

        String token = Jwts.builder().setClaims(claims).setSubject("guest@bms.com").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(keyBytes)).compact();
        log.info(token);*/

    }


}
