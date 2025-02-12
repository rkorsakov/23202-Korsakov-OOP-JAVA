import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class InputHandlerTest {

    @Test
    public void testValidInput() {
        String input = "1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputWithLetters() {
        String input = "12a4\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputWithRepeatingDigits() {
        String input = "1123\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputWithLessThanFourDigits() {
        String input = "123\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputWithMoreThanFourDigits() {
        String input = "12345\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputWithSpecialCharacters() {
        String input = "12@4\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputStartingWithZero() {
        String input = "0123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("0123", result);
    }

    @Test
    public void testMultipleInvalidInputsBeforeValid() {
        String input = "abcd\n12@4\n1123\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testAllZeros() {
        String input = "0000\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testInputWithSpaces() {
        String input = "1 2 3 4\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }

    @Test
    public void testEmptyInput() {
        String input = "\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InputHandler inputHandler = new InputHandler();
        String result = inputHandler.getInput();
        Assert.assertEquals("1234", result);
    }
}