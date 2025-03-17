package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class StackCalculator {
    private static final Logger logger = LoggerFactory.getLogger(StackCalculator.class);

    private final ExecutionContext context;
    private final CommandFactory commandFactory;
    private final Parser parser;

    public StackCalculator() throws IOException {
        this.context = new ExecutionContext();
        this.commandFactory = new CommandFactory();
        this.parser = new Parser();
        logger.info("StackCalculator initialized");
    }

    public void run(String fileName) throws IOException {
        logger.info("Starting execution with file: {}", fileName);
        InputHandler inputHandler = new InputHandler(fileName);

        while (inputHandler.hasNext()) {
            String commandLine = inputHandler.nextLine().trim();
            logger.debug("Read line: {}", commandLine);

            try {
                String[] parts = parser.parse(commandLine);
                if (parts.length == 0) {
                    logger.debug("Skipping empty line");
                    continue;
                }
                String commandName = parts[0];
                if (commandName.startsWith("#")) {
                    logger.debug("Skipping comment line: {}", commandLine);
                    continue;
                }
                String[] args = new String[parts.length - 1];
                System.arraycopy(parts, 1, args, 0, args.length);
                Command command = commandFactory.createCommand(commandName);
                logger.info("Executing command: {}", commandName);
                command.execute(context, args);
                logger.info("Command {} executed successfully", commandName);
            } catch (CommandException e) {
                System.err.println(e.getMessage());
            }
        }
        inputHandler.close();
        logger.info("Execution finished");
    }
}
