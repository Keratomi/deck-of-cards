package hu.deckofcards.game;

import hu.deckofcards.game.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static hu.deckofcards.game.logic.CardCreator.createAListOfCards;

@SpringBootApplication
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAndSaveCards(CardRepository cardRepository) {
        return args -> createAListOfCards().forEach(cardRepository::save);
    }
}
