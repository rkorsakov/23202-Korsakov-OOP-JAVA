import commands.SqrtCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SqrtCommandTest {
    private static final double DELTA = 1e-15;
    private ExecutionContext context;
    private SqrtCommand sqrtCommand;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        sqrtCommand = new SqrtCommand();
    }

    @Test
    public void testSqrtOfPositiveNumber() throws CommandException {
        context.pushStack(9.0);
        sqrtCommand.execute(context, new String[]{});
        assertEquals(3.0, context.peekStack(), DELTA);
    }

    @Test
    public void testSqrtThrowsExceptionForNegativeNumber() {
        context.pushStack(-9.0);
        CommandException exception = assertThrows(CommandException.class, () -> sqrtCommand.execute(context, new String[]{}));
        assertEquals("SQRT command: negative number", exception.getMessage());
    }

    @Test
    public void testSqrtThrowsExceptionIfStackIsEmpty() {
        CommandException exception = assertThrows(CommandException.class, () -> sqrtCommand.execute(context, new String[]{}));
        assertEquals("SQRT command: stack is empty", exception.getMessage());
    }
}
