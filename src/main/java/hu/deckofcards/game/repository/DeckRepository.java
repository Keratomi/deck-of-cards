package hu.deckofcards.game.repository;

import hu.deckofcards.game.entity.Deck;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long> {
}
