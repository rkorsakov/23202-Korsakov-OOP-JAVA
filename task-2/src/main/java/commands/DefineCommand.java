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
            logger.warn("DEFINE command failed: exactly two arguments required");
            throw new CommandException("DEFINE command: exactly two arguments required");
        }
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        String name = args[0];
        double value = parseDouble(args[1], "DEFINE command: invalid number format for value: " + args[1]);
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