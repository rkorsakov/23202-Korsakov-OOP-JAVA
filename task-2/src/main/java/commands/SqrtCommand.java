package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class SqrtCommand implements Command {
    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().isEmpty())
            throw new CommandException("SQRT command: stack is empty");
        double a = executionContext.getStack().pop();
        if (a < 0)
            throw new CommandException("SQRT command: negative number");
        executionContext.getStack().push(Math.sqrt(a));
    }
}
