package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    Properties commands;

    public CommandFactory() throws IOException {
        loadCommands();
    }

    public Command createCommand(String commandName) throws CommandException {
        String className = commands.getProperty(commandName);
        if (className == null) {
            throw new CommandException("Unknown command: " + commandName);
        }
        try {
            return (Command) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CommandException("Failed to create command: " + commandName);
        }
    }

    private void loadCommands() throws IOException {
        InputStream in = CommandFactory.class.getResourceAsStream("/commands.properties");
        if (in == null) {
            throw new IOException("Unable to load commands.properties file");
        }
        commands = new Properties();
        commands.load(in);
        in.close();
    }
}
