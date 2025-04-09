package factory;

import factory.config.FactoryConfig;
import factory.controller.CarStorageController;
import factory.dealer.Dealer;
import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import factory.supplier.Supplier;
import threadpool.ThreadPool;

import java.util.ArrayList;
import java.util.List;

public class Factory {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    private final Supplier<Body> bodySupplier;
    private final Supplier<Engine> engineSupplier;
    private final List<Supplier<Accessory>> accessorySuppliers;

    private final ThreadPool threadPool;
    private final CarStorageController controller;
    private final List<Dealer> dealers;
    private final boolean logSale;

    public Factory(FactoryConfig config) {
        this.bodyStorage = new Storage<>(config.getStorageBodySize());
        this.engineStorage = new Storage<>(config.getStorageEngineSize());
        this.accessoryStorage = new Storage<>(config.getStorageAccessorySize());
        this.carStorage = new Storage<>(config.getStorageAutoSize());
        this.bodySupplier = new Supplier<>(bodyStorage, Body.class, 1500);
        this.engineSupplier = new Supplier<>(engineStorage, Engine.class, 1500);
        this.accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < config.getAccessorySuppliersCount(); i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class, 1500));
        }
        this.threadPool = new ThreadPool(config.getWorkersCount());
        this.controller = new CarStorageController(
                carStorage, threadPool, bodyStorage, engineStorage, accessoryStorage, config.getStorageAutoSize()
        );
        this.dealers = new ArrayList<>();
        this.logSale = config.isLogSale();

        for (int i = 0; i < config.getDealersCount(); i++) {
            dealers.add(new Dealer(carStorage, controller, i + 1, 1000, logSale));
        }
    }

    public void start() {
        bodySupplier.start();
        engineSupplier.start();
        accessorySuppliers.forEach(Thread::start);
        controller.start();
        dealers.forEach(Thread::start);
        System.out.println("Factory started");
    }

    public void stop() {
        dealers.forEach(Dealer::stopDealer);
        controller.interrupt();
        threadPool.shutdown();
        bodySupplier.stopSupplier();
        engineSupplier.stopSupplier();
        accessorySuppliers.forEach(Supplier::stopSupplier);
        System.out.println("Factory stopped");
    }

    public int getCarsProduced() {
        return controller.getAssemblyTask().getCarsProduced();
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

    public Storage<Car> getCarStorage() {
        return carStorage;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public Supplier<Body> getBodySupplier() {
        return bodySupplier;
    }

    public Supplier<Engine> getEngineSupplier() {
        return engineSupplier;
    }

    public List<Supplier<Accessory>> getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }
}