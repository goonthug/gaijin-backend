package com.gaijinent.gaijin_backend.Service;

import com.gaijinent.gaijin_backend.Repository.UserRepository;
import com.gaijinent.gaijin_backend.dto.LoginRequest;
import com.gaijinent.gaijin_backend.dto.RegisterRequest;
import com.gaijinent.gaijin_backend.model.User;
import com.gaijinent.gaijin_backend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest request) {
        // Проверяем, существует ли пользователь
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        // Создаем нового пользователя
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.USER); // Устанавливаем роль по умолчанию

        // Сохраняем пользователя в базу данных
        return userRepository.save(user);
    }

    public User authenticate(LoginRequest request) {
        // Аутентифицируем пользователя
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Возвращаем найденного пользователя
        return userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}