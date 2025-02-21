package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(PrintCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().isEmpty()) {
            logger.error("PRINT command failed: stack is empty");
            throw new CommandException("PRINT command: stack is empty");
        }
        double value = executionContext.getStack().peek();
        System.out.println(value);

        logger.info("Executed PRINT command: {}", value);
    }
}
