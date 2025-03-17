package commands;

import main.CommandException;
import main.UnaryCommand;
import org.slf4j.LoggerFactory;

public class SqrtCommand extends UnaryCommand {
    public SqrtCommand() {
        super(LoggerFactory.getLogger(SqrtCommand.class));
    }

    @Override
    protected double performOperation(double value) throws CommandException {
        if (value < 0) {
            throw new CommandException("SQRT command: negative number");
        }
        return Math.sqrt(value);
    }

    @Override
    protected String getCommandName() {
        return "SQRT";
    }

    @Override
    protected void logExecution(double value, double result) {
        logger.info("Executed SQRT command: sqrt({}) = {}", value, result);
    }
}