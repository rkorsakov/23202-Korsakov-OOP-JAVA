package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class PopCommand implements Command {
    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().isEmpty())
            throw new CommandException("POP command failed: stack is empty");
        executionContext.getStack().pop();
    }
}
