package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(PopCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().isEmpty()) {
            logger.warn("POP command failed: stack is empty");
            throw new CommandException("POP command failed: stack is empty");
        }
        executionContext.getStack().pop();
        logger.info("POP command executed: top value removed from stack");
    }
}