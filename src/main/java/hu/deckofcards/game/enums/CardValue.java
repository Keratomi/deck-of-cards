package hu.deckofcards.game.enums;

public enum CardValue {
    ACE(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9),
    _10(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private int value;

    CardValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
