package commands;

import main.CommandException;
import main.ContextCommand;
import main.ExecutionContext;
import org.slf4j.LoggerFactory;

public class PrintCommand extends ContextCommand {
    public PrintCommand() {
        super(LoggerFactory.getLogger(PrintCommand.class), 0);
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        checkStackNotEmpty(context);
        double value = context.peekStack();
        System.out.println(value);
        logger.info("PRINT command executed: value = {}", value);
    }

    @Override
    protected String getCommandName() {
        return "PRINT";
    }
}