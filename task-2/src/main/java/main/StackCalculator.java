package main;

import java.io.IOException;

public class StackCalculator {
    private final ExecutionContext context;
    private final CommandFactory commandFactory;
    private final Parser parser;

    public StackCalculator() throws IOException {
        this.context = new ExecutionContext();
        this.commandFactory = new CommandFactory();
        this.parser = new Parser();
    }

    public void run(String fileName) throws IOException {
        InputHandler inputHandler = new InputHandler(fileName);
        while (inputHandler.hasNext()) {
            String commandLine = inputHandler.nextLine();
            try {
                String[] parts = parser.parse(commandLine);
                if (parts.length == 0) {
                    continue;
                }
                String commandName = parts[0];
                String[] args = new String[parts.length - 1];
                System.arraycopy(parts, 1, args, 0, args.length);
                if (commandName.startsWith("#")) {
                    continue;
                }
                Command command = commandFactory.createCommand(commandName);
                command.execute(context, args);
            } catch (CommandException e) {
                System.err.println(e.getMessage());
                e.getCause().printStackTrace();
            }
        }
    }
}