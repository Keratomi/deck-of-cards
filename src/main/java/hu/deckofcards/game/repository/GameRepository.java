package hu.deckofcards.game.repository;

import hu.deckofcards.game.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
