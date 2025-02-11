import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

public class GuessValidatorTest {

    private GuessValidator guessValidator;

    @Before
    public void setUp() {
        guessValidator = new GuessValidator();
    }

    @Test
    public void testExactMatch() {
        guessValidator.validateGuess("1234", "1234");
        Assert.assertEquals(4, guessValidator.getBulls());
        Assert.assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testAllCowsNoBulls() {
        guessValidator.validateGuess("1234", "4321");
        Assert.assertEquals(0, guessValidator.getBulls());
        Assert.assertEquals(4, guessValidator.getCows());
    }

    @Test
    public void testSomeBullsSomeCows() {
        guessValidator.validateGuess("1234", "1243");
        Assert.assertEquals(2, guessValidator.getBulls());
        Assert.assertEquals(2, guessValidator.getCows());
    }

    @Test
    public void testSomeBullsNoCows() {
        guessValidator.validateGuess("1234", "1267");
        Assert.assertEquals(2, guessValidator.getBulls());
        Assert.assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testNoBullsNoCows() {
        guessValidator.validateGuess("1234", "5678");
        Assert.assertEquals(0, guessValidator.getBulls());
        Assert.assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testEmptyInputs() {
        guessValidator.validateGuess("", "");
        Assert.assertEquals(0, guessValidator.getBulls());
        Assert.assertEquals(0, guessValidator.getCows());
    }

    @Test
    public void testResetFunctionality() {
        guessValidator.validateGuess("1234", "1234");
        guessValidator.resetCount();
        Assert.assertEquals(0, guessValidator.getBulls());
        Assert.assertEquals(0, guessValidator.getCows());
    }
}
