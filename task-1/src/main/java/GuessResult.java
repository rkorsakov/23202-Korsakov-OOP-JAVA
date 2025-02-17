public class GuessResult {
    private final int bulls;
    private final int cows;

    public GuessResult(int bulls, int cows) {
        this.bulls = bulls;
        this.cows = cows;
    }

    public int getBulls() {
        return bulls;
    }

    public int getCows() {
        return cows;
    }
}
