package factory.controller;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Car;
import factory.product.Engine;
import factory.storage.Storage;
import factory.worker.AssemblingTask;
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
        for (int i = 0; i < targetStockLevel; i++) {
            threadPool.addTask(assemblyTask);
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                synchronized (carStorage) {
                    carStorage.wait();
                    if (!isInterrupted() && carStorage.getSize() < targetStockLevel) {
                        threadPool.addTask(assemblyTask);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Storage controller interrupted");
            Thread.currentThread().interrupt();
        }
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