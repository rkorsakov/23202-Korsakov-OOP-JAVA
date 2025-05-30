package factory.worker;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import threadpool.Task;

public class AssemblingTask implements Task {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;
    private int carsProduced = 0;

    public AssemblingTask(Storage<Body> bodyStorage,
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
            if (engine == null || body == null || accessory == null) {
                engineStorage.put(engine);
                bodyStorage.put(body);
                accessoryStorage.put(accessory);
                return;
            }

            Car car = new Car(body, engine, accessory);
            synchronized (carStorage) {
                carStorage.put(car);
            }
            carsProduced++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Storage<Body> getBodyStorage() {
        return bodyStorage;
    }

    public Storage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public Storage<Accessory> getAccessoryStorage() {
        return accessoryStorage;
    }

    public int getCarsProduced() {
        return carsProduced;
    }
}