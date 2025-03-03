public class Player {
    private final InputHandler inputHandler;

    public Player() {
        this.inputHandler = new InputHandler();
    }

    public String makeGuess() {
        return inputHandler.getInput();
    }
}
