package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class PushCommand implements Command {

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (args.length == 0) {
            throw new CommandException("PUSH command requires an argument");
        }
        try {
            double value = Double.parseDouble(args[0]);
            executionContext.getStack().push(value);
        } catch (NumberFormatException e) {
            throw new CommandException("invalid format for PUSH command argument");
        }
    }
}
