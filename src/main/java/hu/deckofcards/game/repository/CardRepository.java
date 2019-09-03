package hu.deckofcards.game.repository;

import hu.deckofcards.game.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
