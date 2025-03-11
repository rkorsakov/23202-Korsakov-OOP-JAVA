import commands.DefineCommand;
import main.CommandException;
import main.ExecutionContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DefineCommandTest {

    private static final double DELTA = 1e-15;
    private DefineCommand defineCommand;
    private ExecutionContext context;

    @Before
    public void setUp() {
        defineCommand = new DefineCommand();
        context = new ExecutionContext();
    }

    @Test
    public void testDefineValidParameter() throws CommandException {
        defineCommand.execute(context, new String[]{"x", "10"});
        assertEquals(10.0, context.getParameter("x"), DELTA);
    }

    @Test
    public void testDefineWithNumberAsParameterThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> defineCommand.execute(context, new String[]{"10", "15"}));
        assertEquals("DEFINE command: parameter should be a string", exception.getMessage());
    }

    @Test
    public void testDefineWithTooManyArgumentsThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> defineCommand.execute(context, new String[]{"a", "15", "10"}));
        assertEquals("DEFINE command: two arguments required", exception.getMessage());
    }

    @Test
    public void testDefineWithInvalidFormatForParamThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> defineCommand.execute(context, new String[]{"a", "b"}));
        assertEquals("Invalid format for parameter: a", exception.getMessage());
    }
}
