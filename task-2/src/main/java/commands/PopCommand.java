package commands;

import main.CommandException;
import main.ContextCommand;
import main.ExecutionContext;
import org.slf4j.LoggerFactory;

public class PopCommand extends ContextCommand {
    public PopCommand() {
        super(LoggerFactory.getLogger(PopCommand.class), 0);
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        checkStackNotEmpty(context);
        double value = context.popStack();
        logger.info("POP command executed: removed value {}", value);
    }

    @Override
    protected String getCommandName() {
        return "POP";
    }
}