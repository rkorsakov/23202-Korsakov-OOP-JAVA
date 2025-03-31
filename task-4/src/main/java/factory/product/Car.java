package factory.product;

public class Car extends Product {
    private final Body body;
    private final Engine engine;
    private final Accessory accessory;

    public Car(int id, Body body, Engine engine, Accessory accessory) {
        super(id);
        this.body = body;
        this.engine = engine;
        this.accessory = accessory;
    }

    public Body getBody() {
        return body;
    }

    public Engine getEngine() {
        return engine;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    @Override
    public String toString() {
        return String.format("Car<%d>(%s, %s, %s)",
                id, body, engine, accessory);
    }
}