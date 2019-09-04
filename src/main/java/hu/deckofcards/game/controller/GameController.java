package hu.deckofcards.game.controller;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.enums.Display;
import hu.deckofcards.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static hu.deckofcards.game.logic.CardValueCalculator.calculateCardsValue;

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

        return Display.DECK.fillDisplay(createdDeckIds.toArray(Long[]::new));
    }

    @RequestMapping("/shuffleDeck")
    public String shuffleDeck(@RequestParam("deckId") long deckId) {
        List<Card> cardList = gameService.shuffleDeck(deckId);

        return String.format("Deck shuffled. Shuffled list:<br/> %s", cardList.stream().map(Object::toString).collect(Collectors.joining("<br/>")));
    }

    @RequestMapping("/addDeckToGame")
    public String addDeckToGame(@RequestParam("gameId") long gameId, @RequestParam("deckId") long deckId) {
        gameService.addDeckToGame(gameId, deckId);

        return "Deck added";
    }

    @RequestMapping("/addPlayerToGame")
    public String addPlayerToGame(@RequestParam("gameId") long gameId) {
        Long createdPlyerId = gameService.addPlayerToGame(gameId);

        return Display.PLAYER.fillDisplay(createdPlyerId);
    }

    @RequestMapping("/dealCardToAPlayer")
    public String dealCardToAPlayer(@RequestParam("playerId") long playerId) {
        gameService.dealCardToAPlayer(playerId);

        return "Card dealed to a player";
    }

    @RequestMapping("/getAListOfCardsForPlayer")
    public String getAListOfCardsForPlayer(@RequestParam("playerId") long playerId) {
        List<Card> aListOfCardsForPlayer = gameService.getAListOfCardsForPlayer(playerId);

        return Display.CARD_AND_VALUES.fillDisplay(aListOfCardsForPlayer.stream().map(Objects::toString).collect(Collectors.joining("<br/>")), calculateCardsValue(aListOfCardsForPlayer));
    }

    @ExceptionHandler()
    public String handleException(Exception e, WebRequest webRequest) {
        return Display.ERROR.fillDisplay(e.getClass().getName(), e.getMessage());
    }
}
