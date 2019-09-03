package hu.deckofcards.game.repository;

import hu.deckofcards.game.entity.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
}
