package commands;

import main.CommandException;
import main.ContextCommand;
import main.ExecutionContext;
import org.slf4j.LoggerFactory;

public class DefineCommand extends ContextCommand {
    public DefineCommand() {
        super(LoggerFactory.getLogger(DefineCommand.class));
    }

    @Override
    protected void validateArgs(String[] args) throws CommandException {
        if (args.length != 2) {
            logger.warn("DEFINE command failed: two arguments required");
            throw new CommandException("DEFINE command: two arguments required");
        }
        try {
            Double.parseDouble(args[0]);
            logger.error("DEFINE command failed: parameter should be a string");
            throw new CommandException("DEFINE command: parameter should be a string");
        } catch (NumberFormatException _) {
        }
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        String name = args[0];
        double value = parseDouble(args[1], "Invalid format for parameter: " + args[0]);
        context.setParameter(name, value);
        logger.info("DEFINE command executed: defined {} = {}", name, value);
    }

    @Override
    protected String getCommandName() {
        return "DEFINE";
    }

    private double parseDouble(String value, String errorMessage) throws CommandException {
        try {

            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            logger.error(errorMessage);
            throw new CommandException(errorMessage);
        }
    }
}