import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private int attempt;
    private String secretNumber;
    private boolean gameActive;
    private final MessagePrinter messagePrinter;
    private final Player player;

    public Game(Player player) {
        this.player = player;
        this.messagePrinter = new MessagePrinter();
        this.gameActive = false;
        this.attempt = 0;
        generateSecretNum();
    }

    public void startGame() {
        gameActive = true;
        messagePrinter.printHelloMessage();
        final GuessValidator guessValidator = new GuessValidator();
        while (gameActive) {
            String playerGuess = player.makeGuess();
            attempt++;
            guessValidator.validateGuess(secretNumber, playerGuess);
            if (guessValidator.getBulls() == 4) {
                messagePrinter.printWinMessage();
                gameActive = false;
            } else if (attempt < 10) {
                messagePrinter.printGuessState(guessValidator.getBulls(), guessValidator.getCows());
            } else {
                messagePrinter.printLoseMessage();
                messagePrinter.printAnswer(secretNumber);
                gameActive = false;
            }
        }
    }

    public String getSecretNumber() {
        return secretNumber;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public int getAttempt() {
        return attempt;
    }

    private void generateSecretNum() {
        List<Character> numbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        Collections.shuffle(numbers);
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 4; i++)
            number.append(numbers.get(i));
        secretNumber = number.toString();
    }

}
