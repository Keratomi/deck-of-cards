package hu.deckofcards.game.logic;

import hu.deckofcards.game.entity.Card;
import hu.deckofcards.game.enums.CardType;
import hu.deckofcards.game.enums.CardValue;

import java.util.List;
import java.util.stream.Collectors;

import static hu.deckofcards.game.entity.Card.createCardWithTypeAndValue;
import static java.util.Arrays.stream;

public class CardCreator {

    public static List<Card> createAListOfCards() {
        return stream(CardValue.values())
                .flatMap(cardValue -> stream(CardType.values())
                        .map(cardType -> createCardWithTypeAndValue(cardType, cardValue)))
                .collect(Collectors.toList());
    }
}
