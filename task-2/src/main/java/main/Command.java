package main;

public interface Command {
    void execute(ExecutionContext executionContext, String[] args) throws CommandException;
}
