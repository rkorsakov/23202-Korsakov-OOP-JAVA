package commands;

import main.BinaryCommand;
import org.slf4j.LoggerFactory;

public class SubtractCommand extends BinaryCommand {
    public SubtractCommand() {
        super(LoggerFactory.getLogger(AddCommand.class));
    }

    @Override
    protected double performOperation(double a, double b) {
        return a - b;
    }

    @Override
    protected String getCommandName() {
        return "SUBTRACT";
    }

    @Override
    protected void logExecution(double a, double b, double result) {
        logger.info("Executed SUBTRACT command: {} - {} = {}", a, b, result);
    }
}