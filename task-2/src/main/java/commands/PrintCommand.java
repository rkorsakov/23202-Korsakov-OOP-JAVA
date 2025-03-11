package commands;

import main.UnaryCommand;
import org.slf4j.LoggerFactory;

public class PrintCommand extends UnaryCommand {
    public PrintCommand() {
        super(LoggerFactory.getLogger(PrintCommand.class));
    }

    @Override
    protected double performOperation(double value) {
        System.out.println(value);
        return value;
    }

    @Override
    protected String getCommandName() {
        return "PRINT";
    }

    @Override
    protected void logExecution(double value, double result) {
        logger.info("Executed PRINT command: {}", value);
    }
}