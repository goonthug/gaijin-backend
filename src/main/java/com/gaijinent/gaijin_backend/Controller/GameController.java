import com.gaijinent.gaijin_backend.Repository.GameRepository;
import com.gaijinent.gaijin_backend.Repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    // Внедрение через конструктор (если @RequiredArgsConstructor не работает)
    public GameController(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    // ... остальные методы
}