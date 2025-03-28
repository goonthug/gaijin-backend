package com.gaijinent.gaijin_backend.Repository;


import com.gaijinent.gaijin_backend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}