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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

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
        List<Game> allGames = (List) gameRepository.findAll();
        return allGames.stream().map(Game::getId).collect(Collectors.toList());
    }

    public List<Long> createDeck() {
        List<Card> cardList = (List) cardRepository.findAll();
        Deck deck = Deck.createDeckWithCards(cardList);
        deckRepository.save(deck);

        List<Deck> allDecks = (List) deckRepository.findAll();
        return allDecks.stream().map(Deck::getId).collect(Collectors.toList());
    }

    public List<Card> shuffleDeck(Long deckId) {
        Optional<Deck> deckOptional = deckRepository.findById(deckId);

        if (deckOptional.isPresent()) {
            deckOptional.get().shuffleDeck();
            deckRepository.save(deckOptional.get());

            return deckOptional.get().getCardsInDeck();
        }
        return emptyList();
    }

    public Long addPlayerToGame(Long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isPresent()) {
            Player createdPlyer = new Player(gameOptional.get());
            gameOptional.get().addPlayerToGame(playerRepository.save(createdPlyer));

            return createdPlyer.getId();
        }

        return null;
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

        return emptyList();
    }
}
