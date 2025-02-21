package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(PushCommand.class);

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (args.length != 1) {
            logger.warn("PUSH command failed: one argument required");
            throw new CommandException("PUSH command: one argument required");
        }
        double value;
        if (executionContext.getParameters().containsKey(args[0])) {
            value = executionContext.getParameters().get(args[0]);
        } else {
            try {
                value = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                logger.warn("PUSH command failed: Unknown parameter: {}", args[0]);
                throw new CommandException("Unknown parameter: " + args[0]);
            }
        }
        executionContext.getStack().push(value);
        logger.debug("PUSH command executed successfully: pushed {}", value);
    }
}
