package factory.dealer;

import factory.controller.CarStorageController;
import factory.product.Car;
import factory.storage.Storage;

public class Dealer extends Thread {
    private final Storage<Car> carStorage;
    private final CarStorageController controller;
    private volatile boolean isRunning = true;
    private int delayMs;

    public Dealer(Storage<Car> carStorage,
                  CarStorageController controller,
                  int initialDelayMs) {
        this.carStorage = carStorage;
        this.controller = controller;
        this.delayMs = initialDelayMs;
    }

    @Override
    public void run() {
        try {
            while (isRunning && !isInterrupted()) {
                Thread.sleep(delayMs);
                Car car = carStorage.take();
                controller.notifyController();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void setDelay(int newDelayMs) {
        this.delayMs = newDelayMs;
    }

    public void stopDealer() {
        isRunning = false;
        interrupt();
    }
}