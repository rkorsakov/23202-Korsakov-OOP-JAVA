package commands;

import main.CommandException;
import main.ContextCommand;
import main.ExecutionContext;
import org.slf4j.LoggerFactory;

public class PopCommand extends ContextCommand {
    public PopCommand() {
        super(LoggerFactory.getLogger(PopCommand.class));
    }

    @Override
    protected void validateArgs(String[] args) throws CommandException {
        if (args.length > 0) {
            logger.warn("POP command failed: no arguments expected");
            throw new CommandException("POP command: no arguments expected");
        }
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        checkStackNotEmpty(context);
        context.popStack();
        logger.info("POP command executed: top value removed from stack");
    }

    @Override
    protected String getCommandName() {
        return "POP";
    }
}