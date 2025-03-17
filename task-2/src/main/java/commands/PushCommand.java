package commands;

import main.CommandException;
import main.ContextCommand;
import main.ExecutionContext;
import org.slf4j.LoggerFactory;

public class PushCommand extends ContextCommand {
    public PushCommand() {
        super(LoggerFactory.getLogger(PushCommand.class), 1);
    }

    @Override
    protected void executeCommand(ExecutionContext context, String[] args) throws CommandException {
        double value;
        if (context.hasParameter(args[0])) {
            value = context.getParameter(args[0]);
        } else {
            value = parseDouble(args[0], "Invalid number or unknown parameter: " + args[0]);
        }
        context.pushStack(value);
        logger.debug("PUSH command executed successfully: pushed {}", value);
    }

    @Override
    protected String getCommandName() {
        return "PUSH";
    }

    private double parseDouble(String value, String errorMessage) throws CommandException {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            logger.warn(errorMessage);
            throw new CommandException(errorMessage);
        }
    }
}