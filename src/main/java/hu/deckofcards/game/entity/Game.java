package hu.deckofcards.game.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    private static final String GAME_NAME = "Game_";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Deck> decksInGame;
    @OneToMany
    private List<Player> playersInGame;

    public Long getId() {
        return id;
    }

    public void addPlayerToGame(Player player) {
        if (this.playersInGame == null) {
            this.playersInGame = new ArrayList<>();
        }

        this.playersInGame.add(player);
    }

    public void addDeckToGame(Deck deck) {
        if (this.decksInGame == null) {
            this.decksInGame = new ArrayList<>();
        }

        this.decksInGame.add(deck);
    }

    public List<Card> getCardsFromDeck(final int cardCount) {
        List<Card> returnCards = new ArrayList<>();
        int remainingCardCount = cardCount;
        for (Deck deck : this.decksInGame) {
             returnCards.addAll(deck.getCards(remainingCardCount));
            if (returnCards.size() == cardCount) {
                return returnCards;
            }

            remainingCardCount = cardCount - returnCards.size();
            if (remainingCardCount <= 0) {
                return returnCards;
            }
        }

        return returnCards;
    }
}
