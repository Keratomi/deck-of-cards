package hu.deckofcards.game.unit.service;

import hu.deckofcards.game.entity.Game;
import hu.deckofcards.game.repository.GameRepository;
import hu.deckofcards.game.service.GameService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateGameTest {

    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private GameService gameService;

    @Test
    public void shouldGetBackGameNameIfCreateNewGame() {
        Game game = new Game();
        ReflectionTestUtils.setField(game, "id", 12L);
        when(gameRepository.save(any())).thenReturn(game);

        List<Long> gameIds = gameService.createGame();
        Assertions.assertThat(gameIds).containsExactly(12L);
    }
}
