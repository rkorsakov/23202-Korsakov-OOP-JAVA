public class StackCalculator {
    private final ExecutionContext executionContext;

    public StackCalculator() {
        this.executionContext = new ExecutionContext();
    }

    public static void main(String[] args) {
        StackCalculator calculator = new StackCalculator();
    }
}
