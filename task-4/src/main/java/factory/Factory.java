package factory;

import factory.config.FactoryConfig;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import factory.supplier.Supplier;
import factory.worker.Worker;
import threadpool.ThreadPool;

import java.util.ArrayList;

public class Factory {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;
    private final Supplier<Body> bodySupplier;
    private final Supplier<Engine> engineSupplier;
    private final ArrayList<Supplier<Accessory>> accessorySuppliers;
    private final ThreadPool threadPool;

    public Factory(FactoryConfig config) {
        this.bodyStorage = new Storage<>(config.getStorageBodySize());
        this.engineStorage = new Storage<>(config.getStorageEngineSize());
        this.accessoryStorage = new Storage<>(config.getStorageAccessorySize());
        this.carStorage = new Storage<>(config.getStorageAutoSize());
        this.bodySupplier = new Supplier<>(bodyStorage, Body.class, 1000);
        this.engineSupplier = new Supplier<>(engineStorage, Engine.class, 1500);
        this.accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < config.getAccessorySuppliersCount(); i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class, 800));
        }
        this.threadPool = new ThreadPool(config.getWorkersCount());
    }

    public void start() {
        bodySupplier.start();
        engineSupplier.start();
        accessorySuppliers.forEach(Thread::start);
        for (int i = 0; i < 5; i++) {
            threadPool.addTask(new Worker(
                    bodyStorage,
                    engineStorage,
                    accessoryStorage,
                    carStorage
            ));
        }
    }

    public void stop() {
        bodySupplier.stopSupplier();
        engineSupplier.stopSupplier();
        accessorySuppliers.forEach(Supplier::stopSupplier);
        threadPool.shutdown();
    }
}