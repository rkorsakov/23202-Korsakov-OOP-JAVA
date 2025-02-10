import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class InputHandler {
    private final Scanner scanner;
    private final MessagePrinter messagePrinter;
    private static final int LENGTH = 4;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
        this.messagePrinter = new MessagePrinter();
    }

    public String getInput() {
        while (true) {
            System.out.print("Введите 4 уникальные цифры: ");
            String input = scanner.nextLine().trim();
            if (isInputValid(input)) {
                return input;
            }
        }
    }

    private boolean hasOnlyDigits(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRepeatingNums(String input) {
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!uniqueDigits.add(c))
                return true;
        }
        return false;
    }

    private boolean isInputValid(String input) {
        if (!hasOnlyDigits(input)) {
            messagePrinter.printError("должен содержать только цифры!");
            return false;
        }
        if (input.length() != LENGTH) {
            messagePrinter.printError("нужно ввести ровно 4 цифры!");
            return false;
        }
        if (hasRepeatingNums(input)) {
            messagePrinter.printError("цифры не должны повторяться!");
            return false;
        }
        return true;
    }
}
