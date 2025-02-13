import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class GuessValidatorTest {

    private GuessValidator guessValidator;

    @Before
    public void setUp() {
        guessValidator = new GuessValidator();
    }

    @Test
    public void testExactMatch() {
        guessValidator.validateGuess("1234", "1234");
        assertEquals(4, guessValidator.getBulls());
        assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testAllCowsNoBulls() {
        guessValidator.validateGuess("1234", "4321");
        assertEquals(0, guessValidator.getBulls());
        assertEquals(4, guessValidator.getCows());
    }

    @Test
    public void testSomeBullsSomeCows() {
        guessValidator.validateGuess("1234", "1243");
        assertEquals(2, guessValidator.getBulls());
        assertEquals(2, guessValidator.getCows());
    }

    @Test
    public void testSomeBullsNoCows() {
        guessValidator.validateGuess("1234", "1267");
        assertEquals(2, guessValidator.getBulls());
        assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testNoBullsNoCows() {
        guessValidator.validateGuess("1234", "5678");
        assertEquals(0, guessValidator.getBulls());
        assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testEmptyInputs() {
        guessValidator.validateGuess("", "");
        assertEquals(0, guessValidator.getBulls());
        assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testResetFunctionality() {
        guessValidator.validateGuess("1234", "1234");
        guessValidator.resetCount();
        assertEquals(0, guessValidator.getBulls());
        assertEquals(0, guessValidator.getCows());
    }
}
