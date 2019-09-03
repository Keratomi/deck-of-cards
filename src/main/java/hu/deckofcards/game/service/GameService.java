package hu.deckofcards.game.service;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.entity.Deck;
import hu.deckofcards.game.entity.Game;
import hu.deckofcards.game.entity.Player;
import hu.deckofcards.game.repository.CardRepository;
import hu.deckofcards.game.repository.DeckRepository;
import hu.deckofcards.game.repository.GameRepository;
import hu.deckofcards.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private PlayerRepository playerRepository;



    public List<Long> createGame() {
        gameRepository.save(new Game());
        return getAllGameIds();
    }

    public List<Long> createDeck() {
        Iterable<Card> allCards = cardRepository.findAll();
        List<Card> cardList = new ArrayList<>();
        allCards.forEach(cardList::add);
        Deck deck = Deck.createDeckWithCards(cardList);
        deckRepository.save(deck);


        Iterable<Deck> allDecks = deckRepository.findAll();
        List<Long> deckIds = new ArrayList<>();
        allDecks.forEach(oneOfAllDeck -> deckIds.add(oneOfAllDeck.getId()));
        return deckIds;
    }

    public List<Card> shuffleDeck(Long deckId) {
        Optional<Deck> deckOptional = deckRepository.findById(deckId);
        deckOptional.ifPresent(Deck::shuffleDeck);

        return deckOptional.orElse(null).getCardsInDeck(); // nullpointer
    }

    private List<Long> getAllGameIds() {
        Iterable<Game> allGames = gameRepository.findAll();
        List<Long> gameIds = new ArrayList<>();
        allGames.forEach(game -> gameIds.add(game.getId()));
        return gameIds;
    }

    public void addPlayerToGame(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        gameOptional.ifPresent(game -> game.addPlayerToGame(playerRepository.save(new Player(game))));
    }

    public void addDeckToGame(Long gameId, Long deckId) {
        Optional<Deck> deckOptional = deckRepository.findById(deckId);
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (deckOptional.isPresent() && gameOptional.isPresent()) {
            gameOptional.get().addDeckToGame(deckOptional.get());
            gameRepository.save(gameOptional.get());
        }
    }

    public void dealCardToAPlayer(Long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        playerOptional.ifPresent(player -> {
            player.dealCards(1);
            playerRepository.save(player);
        });
    }

    public List<Card> getAListOfCardsForPlayer(Long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isPresent()) {
            return playerOptional.get().getCardsForPlayer();
        }

        return Collections.emptyList();
    }
}
