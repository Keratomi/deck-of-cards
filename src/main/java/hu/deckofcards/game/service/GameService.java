package hu.deckofcards.game.service;

import hu.deckofcards.game.entity.Game;
import hu.deckofcards.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public String createGame() {
        Game createdGame = gameRepository.save(new Game());
        return createdGame.getGameName();
    }
}
