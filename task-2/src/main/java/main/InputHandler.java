package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            scanner = new Scanner(System.in);
        } else {
            scanner = new Scanner(new File(fileName));
        }
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public String nextLine() {
        return scanner.nextLine().trim();
    }

    public void close() {
        scanner.close();
    }
}
