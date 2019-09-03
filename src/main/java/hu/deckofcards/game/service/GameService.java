package hu.deckofcards.game.service;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.entity.Deck;
import hu.deckofcards.game.entity.Game;
import hu.deckofcards.game.repository.CardRepository;
import hu.deckofcards.game.repository.DeckRepository;
import hu.deckofcards.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public String createGame() {
        Game createdGame = gameRepository.save(new Game());
        return createdGame.getGameName();
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
}
