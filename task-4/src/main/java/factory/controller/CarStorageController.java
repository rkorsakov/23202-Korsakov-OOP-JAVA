package factory.controller;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import factory.worker.AssemblingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPool;

public class CarStorageController extends Thread {
    private final Storage<Car> carStorage;
    private final ThreadPool threadPool;
    private final AssemblingTask assemblyTask;
    private final int targetStockLevel;

    public CarStorageController(Storage<Car> carStorage,
                                ThreadPool threadPool,
                                Storage<Body> bodyStorage,
                                Storage<Engine> engineStorage,
                                Storage<Accessory> accessoryStorage,
                                int targetStockLevel) {
        this.carStorage = carStorage;
        this.threadPool = threadPool;
        this.assemblyTask = new AssemblingTask(bodyStorage, engineStorage, accessoryStorage, carStorage);
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
                        threadPool.addTask(assemblyTask);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Storage controller interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private boolean hasEnoughDetails() {
        return assemblyTask.getBodyStorage().getSize() > 0
                && assemblyTask.getEngineStorage().getSize() > 0
                && assemblyTask.getAccessoryStorage().getSize() > 0;
    }

    public void notifyController() {
        synchronized (carStorage) {
            carStorage.notify();
        }
    }

    public AssemblingTask getAssemblyTask() {
        return assemblyTask;
    }
}