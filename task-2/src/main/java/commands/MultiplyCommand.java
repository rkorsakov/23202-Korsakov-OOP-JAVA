package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiplyCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(MultiplyCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().size() < 2) {
            logger.error("MULTIPLY command failed: not enough values in stack");
            throw new CommandException("MULTIPLY command: not enough values in stack");
        }
        double b = executionContext.getStack().pop();
        double a = executionContext.getStack().pop();
        double result = a * b;
        executionContext.getStack().push(result);

        logger.info("Executed MULTIPLY command: {} * {} = {}", a, b, result);
    }
}
