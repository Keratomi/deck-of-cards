package hu.deckofcards.game.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {
    private static final String GAME_NAME = "Game_";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Deck> decksInGame;

    public String getGameName() {
        return GAME_NAME + this.id;
    }
}
