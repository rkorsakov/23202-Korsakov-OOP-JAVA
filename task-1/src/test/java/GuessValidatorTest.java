import junit.framework.Assert;
import junit.framework.TestCase;

public class GuessValidatorTest extends TestCase {

    public void testValidateGuess() {
        String a = "1234";
        GuessValidator gv = new GuessValidator();
        gv.validateGuess(a, "1234");
        Assert.assertEquals(4, gv.getBulls());
        Assert.assertEquals(0, gv.getCows());
        gv.validateGuess(a, "5234");
        Assert.assertEquals(3, gv.getBulls());
        Assert.assertEquals(0, gv.getCows());
        gv.validateGuess(a, "2134");
        Assert.assertEquals(2, gv.getBulls());
        Assert.assertEquals(2, gv.getCows());
        gv.validateGuess(a, "2314");
        Assert.assertEquals(1, gv.getBulls());
        Assert.assertEquals(3, gv.getCows());
        gv.validateGuess(a, "4321");
        Assert.assertEquals(0, gv.getBulls());
        Assert.assertEquals(4, gv.getCows());
    }
}