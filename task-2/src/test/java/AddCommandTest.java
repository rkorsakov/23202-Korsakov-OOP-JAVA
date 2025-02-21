import commands.AddCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AddCommandTest {
    private ExecutionContext context;
    private AddCommand addCommand;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        addCommand = new AddCommand();
    }

    @Test
    public void testAddTwoNumbers() throws CommandException {
        context.getStack().push(2.0);
        context.getStack().push(3.0);
        addCommand.execute(context, new String[]{});
        assertEquals(5.0, context.getStack().peek(), DELTA);
    }

    @Test
    public void testAddThrowsExceptionIfStackIsEmpty() {
        CommandException exception = assertThrows(CommandException.class, () -> addCommand.execute(context, new String[]{}));
        assertEquals("ADD command: not enough values in stack", exception.getMessage());
    }

    @Test
    public void testAddThrowsExceptionIfOnlyOneNumber() {
        context.getStack().push(2.0);
        CommandException exception = assertThrows(CommandException.class, () -> addCommand.execute(context, new String[]{}));
        assertEquals("ADD command: not enough values in stack", exception.getMessage());
    }
}
