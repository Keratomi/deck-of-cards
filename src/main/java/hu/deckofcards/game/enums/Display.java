package hu.deckofcards.game.enums;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum Display {
    WELCOME {
        @Override
        public String ownFillDisplay(Object... displayValues) {
            return "";
        }
    },
    GAME {
        @Override
        public String ownFillDisplay(Object... displayValues) {
            return "Games: " + IntStream.range(0, displayValues.length).mapToObj(i -> "%s").collect(Collectors.joining(", "));
        }
    },
    DECK {
        @Override
        public String ownFillDisplay(Object... displayValues) {
            return "Decks: " + IntStream.range(0, displayValues.length).mapToObj(i -> "%s").collect(Collectors.joining(", "));
        }
    },
    PLAYER {
        @Override
        public String ownFillDisplay(Object... displayValues) {
            return "Player added, id: %s";
        }
    },
    CARD_AND_VALUES {
        @Override
        public String ownFillDisplay(Object... displayValues) {
            return "Cards: %s<br/>Sum values: %s";
        }
    },

    ERROR {
        @Override
        public String ownFillDisplay(Object... displayValues) {
            return "Error occured while trying to handle request.<br/>" +
                    "Error type: %s<br/>" +
                    "Error message: %s<br/>" +
                    "<a href=\"/\">Go back to the main page</a>";
        }
    };

    private static final String HEADER = "Welcome in Deck of cards!<br/><a href=\"/createGame\">Create game</a><br/><a href=\"/createDeck\">Create deck</a><br/><br/>";

    public String fillDisplay(Object ...displayValues) {
        return HEADER + String.format(ownFillDisplay(displayValues), displayValues);
    }
    public abstract String ownFillDisplay(Object... displayValues);
}
