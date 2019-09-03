package hu.deckofcards.game.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private List<Card> cardsInDeck;

    private Deck(List<Card> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public Long getId() {
        return id;
    }

    public List<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    public static Deck createDeckWithCards(List<Card> cardList) {
        return new Deck(cardList);
    }

    public void shuffleDeck() {
        List<Card> shuffled = new ArrayList<>();

        Random random = new Random();
        while (!this.cardsInDeck.isEmpty()) {
            int randomCard = random.nextInt(this.cardsInDeck.size());
            shuffled.add(this.cardsInDeck.remove(randomCard));
        }
        this.cardsInDeck = shuffled;
    }

    public List<Card> getCards(int cardCount) {
        return IntStream.rangeClosed(1, Math.min(cardCount, this.cardsInDeck.size() - 1)).mapToObj(this.cardsInDeck::remove).collect(Collectors.toList());
    }

    Deck() {
    }
}
