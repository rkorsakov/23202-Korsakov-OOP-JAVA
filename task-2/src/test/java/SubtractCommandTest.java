import commands.SubtractCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SubtractCommandTest {
    private static final double DELTA = 1e-15;
    private ExecutionContext context;
    private SubtractCommand subtractCommand;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        subtractCommand = new SubtractCommand();
    }

    @Test
    public void testSubtractTwoNumbers() throws CommandException {
        context.pushStack(5.0);
        context.pushStack(3.0);
        subtractCommand.execute(context, new String[]{});
        assertEquals(2.0, context.peekStack(), DELTA);
    }

    @Test
    public void testSubtractThrowsExceptionIfStackIsEmpty() {
        CommandException exception = assertThrows(CommandException.class, () -> subtractCommand.execute(context, new String[]{}));
        assertEquals("SUBTRACT command: not enough values in stack", exception.getMessage());
    }

    @Test
    public void testSubtractThrowsExceptionIfOnlyOneNumber() {
        context.pushStack(2.0);
        CommandException exception = assertThrows(CommandException.class, () -> subtractCommand.execute(context, new String[]{}));
        assertEquals("SUBTRACT command: not enough values in stack", exception.getMessage());
    }
}
