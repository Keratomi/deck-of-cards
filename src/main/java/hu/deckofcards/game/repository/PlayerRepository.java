package hu.deckofcards.game.repository;

import hu.deckofcards.game.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
