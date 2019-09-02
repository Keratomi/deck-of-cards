package hu.deckofcards.game.controller;

import hu.deckofcards.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/")
    public String welcomeInGame() {
        return "Welcome in Deck of cards";
    }

    @RequestMapping("/createGame")
    public String createGame() {
        String gameName = gameService.createGame();

        return String.format("Empty game created with the following name: %s", gameName);
    }
}
