import factory.Factory;
import factory.product.*;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        Engine engine = new Engine(1);
        Accessory accessory = new Accessory(1);
        Body body = new Body(1);
        Car car = new Car(1, body, engine, accessory);
        System.out.println(car);
    }
}
