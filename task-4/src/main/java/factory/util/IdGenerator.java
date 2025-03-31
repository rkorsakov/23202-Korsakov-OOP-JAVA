package factory.util;

public class IdGenerator {
    private int id = 0;

    public synchronized int generateId() {
        return id++;
    }
}
