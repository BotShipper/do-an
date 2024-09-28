package com.project.pitch_management.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class JwtUtils {

    SecretKey key;

    @Value("${jwt.expiration_time}")
    long EXPIRATION_TIME;

    @Value("${jwt.signer_key}")
    String SIGNER_KEY;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));
        key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // Tạo JWT chứa tên người dùng và thêm thời điểm tạo cùng thời hạn hết hạn
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Tạo mới token
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    //  Lấy tên người dùng
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Kiểm tra tính hợp lệ của JWT dựa trên tên người dùng và việc token có hết hạn hay không
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Trích xuất thông tin từ JWT
    <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    // Kiểm tra xem JWT có hết hạn hay chưa.
    boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
