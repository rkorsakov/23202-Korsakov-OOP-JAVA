package factory.product;

public abstract class Product {
    protected final int id;

    public Product(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}