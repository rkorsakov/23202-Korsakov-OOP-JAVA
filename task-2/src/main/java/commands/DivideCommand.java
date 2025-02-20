package commands;

import main.Command;
import main.CommandException;
import main.ExecutionContext;

public class DivideCommand implements Command {

    @Override
    public void execute(ExecutionContext executionContext, String[] args) throws CommandException {
        if (executionContext.getStack().size() != 2) {
            throw new CommandException("Not enough values in stack for division");
        }
        double b = executionContext.getStack().pop();
        if (b == 0)
            throw new CommandException("Division by zero");
        double a = executionContext.getStack().pop();
        executionContext.getStack().push(a / b);
    }
}