package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application started.");
        try {
            StackCalculator stackCalculator = new StackCalculator();
            String fileName = args.length > 0 ? args[0] : null;
            stackCalculator.run(fileName);
        } catch (Exception e) {
            logger.error("Application error: ", e);
            System.err.println(e.getMessage());
        }
    }
}
