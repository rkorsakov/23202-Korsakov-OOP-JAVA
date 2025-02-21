package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class DefineCommand implements Command {

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (args.length != 2)
            throw new CommandException("DEFINE command: two arguments required");
        String name = args[0];
        try {
            Double.parseDouble(name);
            throw new CommandException("DEFINE command: parameter should be a string");
        } catch (NumberFormatException _) {
        }
        try {
            double value = Double.parseDouble(args[1]);
            executionContext.getParameters().put(args[0], value);
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid format for parameter: " + args[0]);
        }
    }
}
