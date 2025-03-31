package com.gaijinent.gaijin_backend.Controller;

import com.gaijinent.gaijin_backend.Service.AuthService;
import com.gaijinent.gaijin_backend.Service.JwtService;
import com.gaijinent.gaijin_backend.dto.LoginRequest;
import com.gaijinent.gaijin_backend.dto.RegisterRequest;
import com.gaijinent.gaijin_backend.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok("Пользователь зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request,
                                   HttpServletResponse response) {
        User user = authService.authenticate(request);
        String jwt = jwtService.generateToken(user);

        Cookie cookie = new Cookie("jwtToken", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Для HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 дней
        response.addCookie(cookie);

        return ResponseEntity.ok("Успешный вход");
    }
}