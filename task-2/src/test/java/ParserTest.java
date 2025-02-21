import main.Parser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ParserTest {

    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testParseSimpleCommand() {
        String[] result = parser.parse("PUSH 10");
        assertArrayEquals(new String[]{"PUSH", "10"}, result);
    }

    @Test
    public void testParseWithMultipleSpaces() {
        String[] result = parser.parse("  DEFINE   x   15  ");
        assertArrayEquals(new String[]{"DEFINE", "x", "15"}, result);
    }
}
