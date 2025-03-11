package commands;

import main.BinaryCommand;
import org.slf4j.LoggerFactory;

public class AddCommand extends BinaryCommand {
    public AddCommand() {
        super(LoggerFactory.getLogger(AddCommand.class));
    }

    @Override
    protected double performOperation(double a, double b) {
        return a + b;
    }

    @Override
    protected String getCommandName() {
        return "ADD";
    }

    @Override
    protected void logExecution(double a, double b, double result) {
        logger.info("Executed ADD command: {} + {} = {}", a, b, result);
    }
}