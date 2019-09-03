package hu.deckofcards.game.unit.logic;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.enums.CardType;
import hu.deckofcards.game.enums.CardValue;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Index;
import org.junit.Test;

import java.util.List;

import static hu.deckofcards.game.entity.Card.createCardWithTypeAndValue;
import static hu.deckofcards.game.logic.CardCreator.createAListOfCards;

public class CardCreatorTest {
    @Test
    public void shouldCreateDeck() {
        List<Card> aListOfCards = createAListOfCards();

        Assertions.assertThat(aListOfCards)
                .hasSize(52)
                .contains(createCardWithTypeAndValue(CardType.HEART, CardValue.ACE), Index.atIndex(0))
                .contains(createCardWithTypeAndValue(CardType.SPADE, CardValue.ACE), Index.atIndex(1))
                .contains(createCardWithTypeAndValue(CardType.HEART, CardValue._2), Index.atIndex(4))
                .contains(createCardWithTypeAndValue(CardType.SPADE, CardValue._2), Index.atIndex(5)); // just pick some cards, not all
    }
}
