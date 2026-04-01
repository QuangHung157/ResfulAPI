package com.java.ResfulAPI.controller;

import java.time.Instant;

import com.java.ResfulAPI.domain.dto.LoginDTO;
import com.java.ResfulAPI.domain.dto.LoginResponse;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    public AuthController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO) {

        // 1) Nạp username/password vào Spring Security
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword());

        // 2) Xác thực người dùng
        Authentication authentication = authenticationManager.authenticate(authInputToken);

        // 3) Nếu xác thực thành công thì tạo JWT
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtExpiration))
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        // chú ý constructor của bạn đang là (message, token)
        LoginResponse response = new LoginResponse("Đăng nhập thành công", accessToken);

        return ResponseEntity.ok(response);
    }
}