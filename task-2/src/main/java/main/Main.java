package main;

public class Main {
    public static void main(String[] args) {
        try {
            StackCalculator stackCalculator = new StackCalculator();
            String fileName = args.length > 0 ? args[0] : null;
            stackCalculator.run(fileName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}