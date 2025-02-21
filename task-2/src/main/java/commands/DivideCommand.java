package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DivideCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DivideCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().size() < 2) {
            logger.error("DIVIDE command failed: not enough values in stack");
            throw new CommandException("DIVIDE command: not enough values in stack");
        }
        double b = executionContext.getStack().pop();
        if (b == 0) {
            logger.error("DIVIDE command failed: division by zero");
            throw new CommandException("DIVIDE command: division by zero");
        }
        double a = executionContext.getStack().pop();
        double result = a / b;
        executionContext.getStack().push(result);

        logger.info("Executed DIVIDE command: {} / {} = {}", a, b, result);
    }
}
