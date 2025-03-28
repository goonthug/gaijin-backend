package com.gaijinent.gaijin_backend.Repository;



import com.gaijinent.gaijin_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Метод для поиска по username
}