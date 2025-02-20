package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class SubtractCommand implements Command {
    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().size() < 2) {
            throw new CommandException("SUBTRACT command: not enough values in stack");
        }
        double b = executionContext.getStack().pop();
        double a = executionContext.getStack().pop();
        executionContext.getStack().push(a - b);
    }
}
