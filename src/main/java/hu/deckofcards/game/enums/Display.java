package hu.deckofcards.game.enums;

public enum Display {
    WELCOME(""),
    GAME("Games: %s"),
    DECK("Decks: %s");

    private static final String HEADER = "Welcome in Deck of cards!<br/><a href=\"/createGame\">Create game</a><br/><a href=\"/createDeck\">Create deck</a><br/><br/>";

    private String template;

    Display(String template) {
        this.template = template;
    }

    public String fillDisplay(Object ...displayValues) {
        return HEADER + String.format(template, displayValues);
    };
}
