package main;

public class Parser {
    public String[] parse(String input) {
        return input.trim().split("\\s+");
    }
}
