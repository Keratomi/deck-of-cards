package hu.deckofcards.game.logic;

import hu.deckofcards.game.entity.Card;

import java.util.List;

public class CardValueCalculator {

    public static int calculateCardsValue(List<Card> cardList) {
        return cardList.stream().mapToInt(Card::getCardExactValue).sum();
    }
}
