import commands.MultiplyCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MultiplyCommandTest {
    private static final double DELTA = 1e-15;
    private ExecutionContext context;
    private MultiplyCommand multiplyCommand;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        multiplyCommand = new MultiplyCommand();
    }

    @Test
    public void testMultiplyTwoNumbers() throws CommandException {
        context.pushStack(2.0);
        context.pushStack(3.0);
        multiplyCommand.execute(context, new String[]{});
        assertEquals(6.0, context.peekStack(), DELTA);
    }

    @Test
    public void testMultiplyThrowsExceptionIfStackIsEmpty() {
        CommandException exception = assertThrows(CommandException.class, () -> multiplyCommand.execute(context, new String[]{}));
        assertEquals("MULTIPLY command: not enough values in stack", exception.getMessage());
    }

    @Test
    public void testMultiplyThrowsExceptionIfOnlyOneNumber() {
        context.pushStack(2.0);
        CommandException exception = assertThrows(CommandException.class, () -> multiplyCommand.execute(context, new String[]{}));
        assertEquals("MULTIPLY command: not enough values in stack", exception.getMessage());
    }
}
