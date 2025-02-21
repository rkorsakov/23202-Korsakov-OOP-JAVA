import commands.DivideCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DivideCommandTest {
    private ExecutionContext context;
    private DivideCommand divideCommand;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        divideCommand = new DivideCommand();
    }

    @Test
    public void testDivideTwoNumbers() throws CommandException {
        context.getStack().push(6.0);
        context.getStack().push(3.0);
        divideCommand.execute(context, new String[]{});
        assertEquals(2.0, context.getStack().peek(), DELTA);
    }

    @Test
    public void testDivideByZeroThrowsException() {
        context.getStack().push(6.0);
        context.getStack().push(0.0);
        CommandException exception = assertThrows(CommandException.class, () -> divideCommand.execute(context, new String[]{}));
        assertEquals("DIVIDE command: division by zero", exception.getMessage());
    }

    @Test
    public void testDivideThrowsExceptionIfStackIsEmpty() {
        CommandException exception = assertThrows(CommandException.class, () -> divideCommand.execute(context, new String[]{}));
        assertEquals("DIVIDE command: not enough values in stack", exception.getMessage());
    }

    @Test
    public void testDivideThrowsExceptionIfOnlyOneNumber() {
        context.getStack().push(2.0);
        CommandException exception = assertThrows(CommandException.class, () -> divideCommand.execute(context, new String[]{}));
        assertEquals("DIVIDE command: not enough values in stack", exception.getMessage());
    }
}
