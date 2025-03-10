package main;

import org.slf4j.Logger;

public abstract class BinaryCommand implements Command {
    protected final Logger logger;

    public BinaryCommand(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(ExecutionContext context, String[] args) throws CommandException {
        if (context.getStackSize() < 2) {
            throw new CommandException(getCommandName() + " failed: not enough values in stack");
        }
        double b = context.popStack();
        double a = context.popStack();
        double result = performOperation(a, b);
        context.pushStack(result);
        logExecution(a, b, result);
    }

    protected abstract double performOperation(double a, double b) throws CommandException;

    protected abstract String getCommandName();

    protected abstract void logExecution(double a, double b, double result);
}