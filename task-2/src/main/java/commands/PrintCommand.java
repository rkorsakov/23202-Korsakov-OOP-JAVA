package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class PrintCommand implements Command {

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().isEmpty())
            throw new CommandException("Nothing to PRINT");
        System.out.println(executionContext.getStack().peek());
    }

}
