import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private Player player;

    @Before
    public void setUp() {
        player = new Player();
        game = new Game(player);
    }

    @Test
    public void testGenerateSecretNumLength() {
        assertEquals(4, game.getSecretNumber().length());
    }

    @Test
    public void testSecretNumUniqueDigits() {
        String secretNumber = game.getSecretNumber();
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : secretNumber.toCharArray()) {
            uniqueDigits.add(c);
        }
        assertEquals(4, uniqueDigits.size());
    }

    @Test
    public void testWinCondition() {
        player = new Player() {
            @Override
            public String makeGuess() {
                return game.getSecretNumber();
            }
        };
        game = new Game(player);
        game.startGame();
        assertFalse(game.isGameActive());
        assertEquals(1, game.getAttempt());
    }

    @Test
    public void testLoseCondition() {
        String secretNumber;
        do {
            game = new Game(player);
            secretNumber = game.getSecretNumber();
        } while ("1234".equals(secretNumber));
        player = new Player() {
            @Override
            public String makeGuess() {
                return "1234";
            }
        };
        game = new Game(player);
        game.startGame();
        assertFalse(game.isGameActive());
        assertEquals(10, game.getAttempt());
    }
}