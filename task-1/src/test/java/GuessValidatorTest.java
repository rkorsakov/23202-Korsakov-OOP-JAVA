import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GuessValidatorTest {

    private GuessValidator guessValidator;

    @Before
    public void setUp() {
        guessValidator = new GuessValidator();
    }

    @Test
    public void testExactMatch() {
        GuessResult result = guessValidator.validateGuess("1234", "1234");
        assertEquals(4, result.bulls());
        assertEquals(0, result.cows());
    }

    @Test
    public void testAllCowsNoBulls() {
        GuessResult result = guessValidator.validateGuess("1234", "4321");
        assertEquals(0, result.bulls());
        assertEquals(4, result.cows());
    }

    @Test
    public void testSomeBullsSomeCows() {
        GuessResult result = guessValidator.validateGuess("1234", "1243");
        assertEquals(2, result.bulls());
        assertEquals(2, result.cows());
    }

    @Test
    public void testSomeBullsNoCows() {
        GuessResult result = guessValidator.validateGuess("1234", "1267");
        assertEquals(2, result.bulls());
        assertEquals(0, result.cows());
    }

    @Test
    public void testNoBullsNoCows() {
        GuessResult result = guessValidator.validateGuess("1234", "5678");
        assertEquals(0, result.bulls());
        assertEquals(0, result.cows());
    }

    @Test
    public void testEmptyInputs() {
        GuessResult result = guessValidator.validateGuess("", "");
        assertEquals(0, result.bulls());
        assertEquals(0, result.cows());
    }
}
