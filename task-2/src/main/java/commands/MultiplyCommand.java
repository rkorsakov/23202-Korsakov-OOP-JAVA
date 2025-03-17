package commands;

import main.BinaryCommand;
import org.slf4j.LoggerFactory;

public class MultiplyCommand extends BinaryCommand {
    public MultiplyCommand() {
        super(LoggerFactory.getLogger(MultiplyCommand.class));
    }

    @Override
    protected double performOperation(double a, double b) {
        return a * b;
    }

    @Override
    protected String getCommandName() {
        return "MULTIPLY";
    }

    @Override
    protected void logExecution(double a, double b, double result) {
        logger.info("Executed MULTIPLY command: {} * {} = {}", a, b, result);
    }
}