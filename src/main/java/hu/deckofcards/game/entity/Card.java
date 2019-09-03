package hu.deckofcards.game.entity;

import hu.deckofcards.game.enums.CardType;
import hu.deckofcards.game.enums.CardValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private CardType cardType;
    private CardValue cardValue;

    public static Card createCardWithTypeAndValue(CardType cardType, CardValue cardValue) {
        Card card = new Card();
        card.cardType = cardType;
        card.cardValue = cardValue;
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardType == card.cardType &&
                cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardType, cardValue);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType=" + cardType +
                ", cardValue=" + cardValue +
                '}';
    }
}
