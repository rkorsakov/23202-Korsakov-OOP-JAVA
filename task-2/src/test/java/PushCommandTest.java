import commands.PushCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PushCommandTest {

    private static final double DELTA = 1e-15;
    private ExecutionContext context;
    private PushCommand pushCommand;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        pushCommand = new PushCommand();
    }

    @Test
    public void testPushNumberOntoStack() throws CommandException {
        pushCommand.execute(context, new String[]{"5.5"});
        assertEquals(1, context.getStack().size());
        assertEquals(5.5, context.getStack().peek(), DELTA);
    }

    @Test
    public void testPushUnknownParameterThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> pushCommand.execute(context, new String[]{"unknown"}));
        assertEquals("Unknown parameter: unknown", exception.getMessage());
    }

    @Test
    public void testPushThrowsExceptionForNoArguments() {
        CommandException exception = assertThrows(CommandException.class, () -> pushCommand.execute(context, new String[]{}));
        assertEquals("PUSH command: one argument required", exception.getMessage());
    }

    @Test
    public void testPushThrowsExceptionForTooManyArguments() {
        CommandException exception = assertThrows(CommandException.class, () -> pushCommand.execute(context, new String[]{"3.14", "2.71"}));
        assertEquals("PUSH command: one argument required", exception.getMessage());
    }
}
