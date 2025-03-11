package main;

import org.slf4j.Logger;

public abstract class UnaryCommand implements Command {
    protected final Logger logger;

    public UnaryCommand(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(ExecutionContext context, String[] args) throws CommandException {
        validateArgs(args);
        if (context.isStackEmpty()) {
            throw new CommandException(getCommandName() + " command: stack is empty");
        }
        double value = context.popStack();
        double result = performOperation(value);
        context.pushStack(result);
        logExecution(value, result);
    }

    protected void validateArgs(String[] args) throws CommandException {
        if (args.length > 0) {
            throw new CommandException(getCommandName() + " command: no arguments expected");
        }
    }

    protected abstract double performOperation(double value) throws CommandException;
    protected abstract String getCommandName();
    protected abstract void logExecution(double value, double result);
}