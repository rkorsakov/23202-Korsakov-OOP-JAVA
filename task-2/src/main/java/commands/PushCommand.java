package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class PushCommand implements Command {

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("PUSH command: one argument required");
        }
            double value;
            if (executionContext.getParameters().containsKey(args[0]))
                value = executionContext.getParameters().get(args[0]);
            else
                try {
                    value = Double.parseDouble(args[0]);
                } catch (NumberFormatException e) {
                    throw new CommandException("Unknown parameter: " + args[0]);
                }
            executionContext.getStack().push(value);
    }
}
