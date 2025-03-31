package factory.product;

public class Engine extends Product {
    public Engine(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Engine: " + "<" + id + ">";
    }
}