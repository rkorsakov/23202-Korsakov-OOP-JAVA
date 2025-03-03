public class GuessValidator {
    public GuessResult validateGuess(String secretNumber, String playerGuess) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < secretNumber.length(); i++) {
            if (secretNumber.charAt(i) == playerGuess.charAt(i)) {
                bulls++;
            } else if (playerGuess.contains(secretNumber.charAt(i) + "")) {
                cows++;
            }
        }
        return new GuessResult(bulls, cows);
    }
}
