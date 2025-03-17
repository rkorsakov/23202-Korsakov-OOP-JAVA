package commands;

import main.BinaryCommand;
import main.CommandException;
import org.slf4j.LoggerFactory;

public class DivideCommand extends BinaryCommand {
    public DivideCommand() {
        super(LoggerFactory.getLogger(DivideCommand.class));
    }

    @Override
    protected double performOperation(double a, double b) throws CommandException {
        if (b == 0) {
            throw new CommandException("Division by zero");
        }
        return a / b;
    }

    @Override
    protected String getCommandName() {
        return "DIVIDE";
    }

    @Override
    protected void logExecution(double a, double b, double result) {
        logger.info("Executed DIVIDE command: {} / {} = {}", a, b, result);
    }
}