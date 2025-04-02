package factory.worker;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import threadpool.Task;

public class Worker implements Task {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    public Worker(Storage<Body> bodyStorage,
                  Storage<Engine> engineStorage,
                  Storage<Accessory> accessoryStorage,
                  Storage<Car> carStorage) {
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    @Override
    public void execute() {
        try {
            Body body = bodyStorage.take();
            Engine engine = engineStorage.take();
            Accessory accessory = accessoryStorage.take();
            Car car = new Car(body, engine, accessory);
            carStorage.put(car);
            System.out.println("Машина собрана");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}