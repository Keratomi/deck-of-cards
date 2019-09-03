package hu.deckofcards.game.controller;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping("/createDeck")
    public String createDeck() {
        List<Long> createdDeckIds = gameService.createDeck();

        return String.format("New deck created. Probably need to shuffle. Available deck ids: %s", createdDeckIds.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }

    @RequestMapping("/shuffleDeck")
    public String shuffleDeck(/*need param*/) {
        List<Card> cardList = gameService.shuffleDeck(1L);

        return String.format("Deck shuffled. Shuffled list: %s", cardList.stream().map(Object::toString).collect(Collectors.joining("<br/>")));
    }
}
