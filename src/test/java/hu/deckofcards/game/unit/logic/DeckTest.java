package hu.deckofcards.game.unit.logic;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.entity.Deck;
import hu.deckofcards.game.enums.CardType;
import hu.deckofcards.game.enums.CardValue;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hu.deckofcards.game.entity.Card.createCardWithTypeAndValue;

public class DeckTest {

    @Test
    public void shouldCreateDeck() {
        Deck deck = Deck.createDeckWithCards(Arrays.asList(
                createCardWithTypeAndValue(CardType.HEART, CardValue.ACE),
                createCardWithTypeAndValue(CardType.SPADE, CardValue._2)

        ));

        Assertions.assertThat(deck.getCardsInDeck())
                .containsExactly(
                        createCardWithTypeAndValue(CardType.HEART, CardValue.ACE),
                        createCardWithTypeAndValue(CardType.SPADE, CardValue._2));
    }

    @Test
    public void shouldShuffleDeck() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(createCardWithTypeAndValue(CardType.HEART, CardValue.ACE));
        cardList.add(createCardWithTypeAndValue(CardType.SPADE, CardValue.ACE));
        cardList.add(createCardWithTypeAndValue(CardType.HEART, CardValue._2));
        cardList.add(createCardWithTypeAndValue(CardType.SPADE, CardValue._2));

        Deck deck = Deck.createDeckWithCards(cardList);
        deck.shuffleDeck();

        Assertions.assertThat(deck.getCardsInDeck())
                .hasSize(4)
                .contains(
                        createCardWithTypeAndValue(CardType.HEART, CardValue.ACE),
                        createCardWithTypeAndValue(CardType.SPADE, CardValue.ACE),
                        createCardWithTypeAndValue(CardType.HEART, CardValue._2),
                        createCardWithTypeAndValue(CardType.SPADE, CardValue._2));
    }
}
