package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubtractCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(SubtractCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().size() < 2) {
            logger.error("SUBTRACT command failed: not enough values in stack");
            throw new CommandException("SUBTRACT command: not enough values in stack");
        }
        double b = executionContext.getStack().pop();
        double a = executionContext.getStack().pop();
        double result = a - b;
        executionContext.getStack().push(result);
        logger.info("Executed SUBTRACT command: {} - {} = {}", a, b, result);
    }
}
