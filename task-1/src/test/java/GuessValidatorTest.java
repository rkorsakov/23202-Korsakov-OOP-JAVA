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
        assertEquals(4, result.getBulls());
        assertEquals(0, result.getCows());
    }

    @Test
    public void testAllCowsNoBulls() {
        GuessResult result = guessValidator.validateGuess("1234", "4321");
        assertEquals(0, result.getBulls());
        assertEquals(4, result.getCows());
    }

    @Test
    public void testSomeBullsSomeCows() {
        GuessResult result = guessValidator.validateGuess("1234", "1243");
        assertEquals(2, result.getBulls());
        assertEquals(2, result.getCows());
    }

    @Test
    public void testSomeBullsNoCows() {
        GuessResult result = guessValidator.validateGuess("1234", "1267");
        assertEquals(2, result.getBulls());
        assertEquals(0, result.getCows());
    }

    @Test
    public void testNoBullsNoCows() {
        GuessResult result = guessValidator.validateGuess("1234", "5678");
        assertEquals(0, result.getBulls());
        assertEquals(0, result.getCows());
    }

    @Test
    public void testEmptyInputs() {
        GuessResult result = guessValidator.validateGuess("", "");
        assertEquals(0, result.getBulls());
        assertEquals(0, result.getCows());
    }
}
