package main;

import org.slf4j.Logger;

public abstract class ContextCommand implements Command {
    protected final Logger logger;
    protected final int expectedArgsCount;

    public ContextCommand(Logger logger, int expectedArgsCount) {
        this.logger = logger;
        this.expectedArgsCount = expectedArgsCount;
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

    protected void validateArgs(String[] args) throws CommandException {
        if (args.length != expectedArgsCount) {
            throw new CommandException(
                    getCommandName() + " command: expected " + expectedArgsCount +
                            " arguments, but got " + args.length
            );
        }
    }
    protected abstract void executeCommand(ExecutionContext context, String[] args) throws CommandException;

    protected abstract String getCommandName();
}