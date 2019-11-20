package hu.deckofcards.game.controller;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.enums.Display;
import hu.deckofcards.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static hu.deckofcards.game.logic.CardValueCalculator.calculateCardsValue;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String welcomeInGame() {
        return Display.WELCOME.fillDisplay();
    }

    @RequestMapping(value = "/createGame", method = RequestMethod.GET)
    public String createGame() {
        List<Long> gameIds = gameService.createGame();

        return Display.GAME.fillDisplay(gameIds.toArray(Long[]::new));
    }

    @RequestMapping(path = "/createDeck", method = RequestMethod.GET)
    public String createDeck() {
        List<Long> createdDeckIds = gameService.createDeck();

        return Display.DECK.fillDisplay(createdDeckIds.toArray(Long[]::new));
    }

    @RequestMapping(path = "/shuffleDeck", method = RequestMethod.GET)
    public String shuffleDeck(@RequestParam("deckId") long deckId) {
        List<Card> cardList = gameService.shuffleDeck(deckId);

        return String.format("Deck shuffled. Shuffled list:<br/> %s", cardList.stream().map(Object::toString).collect(Collectors.joining("<br/>")));
    }

    @RequestMapping(path = "/addDeckToGame", method = RequestMethod.PUT)
    public String addDeckToGame(@RequestParam("gameId") long gameId, @RequestParam("deckId") long deckId) {
        gameService.addDeckToGame(gameId, deckId);

        return "Deck added";
    }

    @RequestMapping(path = "/addPlayerToGame", method = RequestMethod.PUT)
    public String addPlayerToGame(@RequestParam("gameId") long gameId) {
        Long createdPlyerId = gameService.addPlayerToGame(gameId);

        return Display.PLAYER.fillDisplay(createdPlyerId);
    }

    @RequestMapping(path = "/dealCardToAPlayer", method = RequestMethod.PUT)
    public String dealCardToAPlayer(@RequestParam("playerId") long playerId) {
        gameService.dealCardToAPlayer(playerId);

        return "Card dealed to a player";
    }

    @RequestMapping(path = "/getAListOfCardsForPlayer", method = RequestMethod.GET)
    public String getAListOfCardsForPlayer(@RequestParam("playerId") long playerId) {
        List<Card> aListOfCardsForPlayer = gameService.getAListOfCardsForPlayer(playerId);

        return Display.CARD_AND_VALUES.fillDisplay(aListOfCardsForPlayer.stream().map(Objects::toString).collect(Collectors.joining("<br/>")), calculateCardsValue(aListOfCardsForPlayer));
    }

    @ExceptionHandler()
    public String handleException(Exception e, WebRequest webRequest) {
        return Display.ERROR.fillDisplay(e.getClass().getName(), e.getMessage());
    }
}
