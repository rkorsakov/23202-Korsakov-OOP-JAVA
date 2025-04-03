package factory.controller;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import factory.worker.Worker;
import threadpool.ThreadPool;

public class CarStorageController extends Thread {
    private final Storage<Car> carStorage;
    private final ThreadPool threadPool;
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final int targetStockLevel;

    public CarStorageController(Storage<Car> carStorage,
                                ThreadPool threadPool,
                                Storage<Body> bodyStorage,
                                Storage<Engine> engineStorage,
                                Storage<Accessory> accessoryStorage,
                                int targetStockLevel) {
        this.carStorage = carStorage;
        this.threadPool = threadPool;
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.targetStockLevel = targetStockLevel;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                synchronized (carStorage) {
                    while (carStorage.getSize() >= targetStockLevel && !isInterrupted()) {
                        carStorage.wait();
                    }
                    if (hasEnoughDetails() && !isInterrupted()) {
                        threadPool.addTask(
                                new Worker(bodyStorage, engineStorage, accessoryStorage, carStorage)
                        );
                    } else if (isInterrupted()) break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean hasEnoughDetails() {
        return bodyStorage.getSize() > 0
                && engineStorage.getSize() > 0
                && accessoryStorage.getSize() > 0;
    }

    public void notifyController() {
        synchronized (carStorage) {
            carStorage.notify();
        }
    }
}