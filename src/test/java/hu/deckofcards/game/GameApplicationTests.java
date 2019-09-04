package hu.deckofcards.game;

import hu.deckofcards.game.service.GameService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-test.properties")
public class GameApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private GameService gameService;

	@Test
	public void shouldCreateGame() throws Exception {
		mvc.perform(get("/createGame")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(Matchers.containsString("Games:")));
	}

	@Test
	public void shouldCreateDeck() throws Exception {
		mvc.perform(get("/createDeck")
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(Matchers.containsString("Decks:")));
	}

	@Test
	public void shouldShuffleDeck() throws Exception {
		List<Long> deckIds = gameService.createDeck();

		mvc.perform(get("/shuffleDeck?deckId=" + deckIds.get(0))
				.contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string(Matchers.containsString("Deck shuffled. Shuffled list:")));
	}
}
