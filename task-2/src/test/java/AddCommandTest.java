import commands.AddCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class AddCommandTest {
    private ExecutionContext context;
    private AddCommand addCommand;

    @Before
    public void setUp() {
        context = Mockito.mock(ExecutionContext.class);
        addCommand = new AddCommand();
    }

    @Test
    public void testAddTwoNumbers() throws CommandException {
        when(context.getStackSize()).thenReturn(2);
        when(context.popStack()).thenReturn(3.0).thenReturn(2.0);
        addCommand.execute(context, new String[]{});
        Mockito.verify(context).pushStack(5.0);
    }

    @Test
    public void testAddThrowsExceptionNotEnoughVals() {
        when(context.getStackSize()).thenReturn(1);
        CommandException exception = assertThrows(CommandException.class, () -> addCommand.execute(context, new String[]{}));
        assertEquals("ADD command: not enough values in stack", exception.getMessage());
    }
}
