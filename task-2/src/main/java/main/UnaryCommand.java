package main;

import org.slf4j.Logger;

public abstract class UnaryCommand implements Command {
    protected final Logger logger;

    public UnaryCommand(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(ExecutionContext context, String[] args) throws CommandException {
        if (context.isStackEmpty()) {
            throw new CommandException(getCommandName() + " failed: stack is empty");
        }
        double value = context.popStack();
        double result = performOperation(value);
        context.pushStack(result);
        logExecution(value, result);
    }

    protected abstract double performOperation(double value) throws CommandException;

    protected abstract String getCommandName();

    protected abstract void logExecution(double value, double result);
}
