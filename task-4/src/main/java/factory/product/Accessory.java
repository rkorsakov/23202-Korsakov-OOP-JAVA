package factory.product;

public class Accessory extends Product {
    public Accessory(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Accessory: " + "<" + id + ">";
    }
}