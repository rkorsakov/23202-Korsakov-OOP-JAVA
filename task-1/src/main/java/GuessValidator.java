public class GuessValidator {

    private int bulls;
    private int cows;

    public void validateGuess(String secretNumber, String playerGuess) {
        resetCount();
        for (int i = 0; i < secretNumber.length(); i++) {
            if (secretNumber.charAt(i) == playerGuess.charAt(i)) {
                bulls++;
            } else if (playerGuess.contains(secretNumber.charAt(i) + "")) {
                cows++;
            }
        }
    }

    public void resetCount() {
        bulls = 0;
        cows = 0;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }
}
