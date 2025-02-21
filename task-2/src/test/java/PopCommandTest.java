import commands.PopCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopCommandTest {

    private ExecutionContext context;
    private PopCommand popCommand;

    @Before
    public void setUp() {
        context = new ExecutionContext();
        popCommand = new PopCommand();
    }

    @Test
    public void testPopFromStack() throws CommandException {
        context.getStack().push(5.0);
        popCommand.execute(context, new String[]{});
        assertTrue(context.getStack().isEmpty());
    }

    @Test
    public void testPopFromEmptyStackThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> popCommand.execute(context, new String[]{}));
        assertEquals("POP command failed: stack is empty", exception.getMessage());
    }
}
