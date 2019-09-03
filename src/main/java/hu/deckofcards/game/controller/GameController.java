package hu.deckofcards.game.controller;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.enums.Display;
import hu.deckofcards.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class GameController {



    @Autowired
    private GameService gameService;

    @RequestMapping("/")
    public String welcomeInGame() {
        return Display.WELCOME.fillDisplay();
    }

    @RequestMapping("/createGame")
    public String createGame() {
        List<Long> gameIds = gameService.createGame();

        return Display.GAME.fillDisplay(gameIds.toArray(Long[]::new));
    }

    @RequestMapping("/createDeck")
    public String createDeck() {
        List<Long> createdDeckIds = gameService.createDeck();

        return "New deck created. Probably need to shuffle. Available deck ids (click to shuffle): " +
                createdDeckIds.stream().map(id -> String.format("<a href=\"/shuffleDeck?deckId=%s\">%s</a>", id, id)).collect(Collectors.joining(", "));
    }

    @RequestMapping("/shuffleDeck")
    public String shuffleDeck(@RequestParam("deckId") long deckId) { // if not exists param throw error
        List<Card> cardList = gameService.shuffleDeck(deckId);

        return String.format("Deck shuffled. Shuffled list:<br/> %s", cardList.stream().map(Object::toString).collect(Collectors.joining("<br/>")));
    }

    @RequestMapping("/addDeckToGame")
    public String addDeckToGame(@RequestParam("gameId") long gameId, @RequestParam("deckId") long deckId) { // if not exists param throw error
        gameService.addDeckToGame(gameId, deckId);

        return "Deck added";
    }

    @RequestMapping("/addPlayerToGame")
    public String addPlayerToGame(@RequestParam("gameId") long gameId) { // if not exists param throw error
        gameService.addPlayerToGame(gameId);

        return "Player added";
    }

    @RequestMapping("/dealCardToAPlayer")
    public String dealCardToAPlayer(@RequestParam("playerId") long playerId) { // if not exists param throw error
        gameService.dealCardToAPlayer(playerId);

        return "Card dealed to a player";
    }

    @RequestMapping("/getAListOfCardsForPlayer")
    public String getAListOfCardsForPlayer(@RequestParam("playerId") long playerId) { // if not exists param throw error
        List<Card> aListOfCardsForPlayer = gameService.getAListOfCardsForPlayer(playerId);

        return aListOfCardsForPlayer.stream().map(Objects::toString).collect(Collectors.joining("<br/>"));
    }
}
