import commands.PopCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
        context.pushStack(5.0);
        popCommand.execute(context, new String[]{});
        assertEquals(0, context.getStackSize());
    }

    @Test
    public void testPopFromEmptyStackThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> popCommand.execute(context, new String[]{}));
        assertEquals("POP command: stack is empty", exception.getMessage());
    }
}
