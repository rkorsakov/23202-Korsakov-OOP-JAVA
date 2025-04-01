import factory.Factory;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory(6, 6, 6);
        Engine engine = new Engine();
        Accessory accessory = new Accessory();
        Body body = new Body();
        Car car = new Car(body, engine, accessory);
        System.out.println(car);
        Storage<Body> bodyStorage = new Storage<>(5);
        try {
            bodyStorage.put(body);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
