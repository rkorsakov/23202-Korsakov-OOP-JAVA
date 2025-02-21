package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqrtCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(SqrtCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().isEmpty()) {
            logger.error("SQRT command failed: stack is empty");
            throw new CommandException("SQRT command: stack is empty");
        }
        double a = executionContext.getStack().pop();
        if (a < 0) {
            logger.error("SQRT command failed: negative number {}", a);
            throw new CommandException("SQRT command: negative number");
        }
        double result = Math.sqrt(a);
        executionContext.getStack().push(result);

        logger.info("Executed SQRT command: sqrt({}) = {}", a, result);
    }
}
