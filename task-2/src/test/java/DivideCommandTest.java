import commands.DivideCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DivideCommandTest {
    private static final double DELTA = 1e-15;
    private ExecutionContext context;
    private DivideCommand divideCommand;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        divideCommand = new DivideCommand();
    }

    @Test
    public void testDivideTwoNumbers() throws CommandException {
        context.pushStack(6.0);
        context.pushStack(3.0);
        divideCommand.execute(context, new String[]{});
        assertEquals(2.0, context.peekStack(), DELTA);
    }

    @Test
    public void testDivideByZeroThrowsException() {
        context.pushStack(6.0);
        context.pushStack(0.0);
        CommandException exception = assertThrows(CommandException.class, () -> divideCommand.execute(context, new String[]{}));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    public void testDivideThrowsExceptionIfStackIsEmpty() {
        CommandException exception = assertThrows(CommandException.class, () -> divideCommand.execute(context, new String[]{}));
        assertEquals("DIVIDE command: not enough values in stack", exception.getMessage());
    }

    @Test
    public void testDivideThrowsExceptionIfOnlyOneNumber() {
        context.pushStack(2.0);
        CommandException exception = assertThrows(CommandException.class, () -> divideCommand.execute(context, new String[]{}));
        assertEquals("DIVIDE command: not enough values in stack", exception.getMessage());
    }
}
