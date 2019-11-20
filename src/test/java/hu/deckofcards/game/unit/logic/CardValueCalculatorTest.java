package hu.deckofcards.game.unit.logic;

import hu.deckofcards.game.enums.CardType;
import hu.deckofcards.game.enums.CardValue;
import hu.deckofcards.game.logic.CardValueCalculator;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

import static hu.deckofcards.game.entity.Card.createCardWithTypeAndValue;

public class CardValueCalculatorTest {

    @Test
    public void shouldCalculateCardsValue() {
        int sumValue = CardValueCalculator.calculateCardsValue(Arrays.asList(
                createCardWithTypeAndValue(CardType.HEART, CardValue.CARD_ACE),
                createCardWithTypeAndValue(CardType.HEART, CardValue.CARD_2),
                createCardWithTypeAndValue(CardType.HEART, CardValue.CARD_KING),
                createCardWithTypeAndValue(CardType.HEART, CardValue.CARD_8),
                createCardWithTypeAndValue(CardType.HEART, CardValue.CARD_JACK)
        ));

        Assertions.assertThat(sumValue).isEqualTo(35);
    }
}
