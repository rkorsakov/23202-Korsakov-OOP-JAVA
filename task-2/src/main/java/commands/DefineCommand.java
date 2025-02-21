package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefineCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DefineCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (args.length != 2) {
            logger.warn("DEFINE command failed: two arguments required");
            throw new CommandException("DEFINE command: two arguments required");
        }
        String name = args[0];
        try {
            Double.parseDouble(name);
            logger.error("DEFINE command failed: parameter should be a string");
            throw new CommandException("DEFINE command: parameter should be a string");
        } catch (NumberFormatException _) {
        }
        try {
            double value = Double.parseDouble(args[1]);
            executionContext.getParameters().put(args[0], value);
            logger.info("DEFINE command executed: {} = {}", name, value);
        } catch (NumberFormatException e) {
            logger.error("DEFINE command failed: Invalid format for parameter: {}", args[0]);
            throw new CommandException("Invalid format for parameter: " + args[0]);
        }
    }
}