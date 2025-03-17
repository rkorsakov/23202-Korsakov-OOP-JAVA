import commands.PushCommand;
import main.Command;
import main.CommandException;
import main.CommandFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CommandFactoryTest {
    private CommandFactory commandFactory;

    @Before
    public void setUp() throws IOException {
        commandFactory = new CommandFactory();
    }

    @Test
    public void testCreateExistingCommand() throws CommandException {
        Command command = commandFactory.createCommand("PUSH");
        assertNotNull(command);
        assertTrue(command instanceof PushCommand);
    }

    @Test
    public void testCreateUnknownCommandThrowsException() {
        CommandException exception = assertThrows(CommandException.class,
                () -> commandFactory.createCommand("UNKNOWN_COMMAND"));
        assertEquals("Unknown command: UNKNOWN_COMMAND", exception.getMessage());
    }
}
