package hu.deckofcards.game.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Game game;
    @ManyToMany
    private List<Card> cardsForPlayer;


    public Player(Game game) {
        this.game = game;
    }

    public List<Card> getCardsForPlayer() {
        return cardsForPlayer;
    }

    public void dealCards(int cardCount) {
        List<Card> cardsFromDeck = this.game.getCardsFromDeck(cardCount);
        if (this.cardsForPlayer == null) {
            this.cardsForPlayer = new ArrayList<>();
        }

        this.cardsForPlayer.addAll(cardsFromDeck);
    }

    public Long getId() {
        return id;
    }

    Player() {
    }
}
