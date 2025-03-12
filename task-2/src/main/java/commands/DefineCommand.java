package commands;

import main.CommandException;
import main.ContextCommand;
import main.ExecutionContext;
import org.slf4j.LoggerFactory;

public class DefineCommand extends ContextCommand {
    public DefineCommand() {
        super(LoggerFactory.getLogger(DefineCommand.class), 2);
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        String name = args[0];
        try {
            Double.parseDouble(name);
            logger.error("DEFINE command failed: parameter should be a string");
            throw new CommandException("DEFINE command: parameter should be a string");
        } catch (NumberFormatException _) {
        }
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