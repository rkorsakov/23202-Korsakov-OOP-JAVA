package factory.dealer;

import factory.controller.CarStorageController;
import factory.product.Car;
import factory.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dealer extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(Dealer.class);
    private final Storage<Car> carStorage;
    private final CarStorageController controller;
    private final int number;
    private volatile boolean isRunning = true;
    private int delayMs;
    private final boolean logSale;

    public Dealer(Storage<Car> carStorage, CarStorageController controller, int number, int initialDelayMs, boolean logSale) {
        this.carStorage = carStorage;
        this.controller = controller;
        this.number = number;
        this.delayMs = initialDelayMs;
        this.logSale = logSale;
    }

    @Override
    public void run() {
        try {
            while (isRunning && !isInterrupted()) {
                Thread.sleep(delayMs);
                Car car = carStorage.take();
                if (logSale) {
                    logSale(car);
                }
                controller.notifyController();
            }
        } catch (InterruptedException e) {
            logger.info("Dealer {} interrupted", number);
            Thread.currentThread().interrupt();
        }
    }

    private void logSale(Car car) {
        logger.info("Dealer {}: {})",
                number, car.toString());
    }

    public void setDelay(int newDelayMs) {
        this.delayMs = newDelayMs;
    }

    public void stopDealer() {
        isRunning = false;
        interrupt();
    }
}