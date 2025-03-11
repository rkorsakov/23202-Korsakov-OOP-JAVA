package main;

import org.slf4j.Logger;

public abstract class ContextCommand implements Command {
    protected final Logger logger;

    public ContextCommand(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute(ExecutionContext context, String[] args) throws CommandException {
        validateArgs(args);
        executeCommand(context, args);
    }

    protected void checkStackNotEmpty(ExecutionContext context) throws CommandException {
        if (context.isStackEmpty()) {
            throw new CommandException(getCommandName() + " command: stack is empty");
        }
    }

    protected abstract void validateArgs(String[] args) throws CommandException;

    protected abstract void executeCommand(ExecutionContext context, String[] args) throws CommandException;

    protected abstract String getCommandName();
}