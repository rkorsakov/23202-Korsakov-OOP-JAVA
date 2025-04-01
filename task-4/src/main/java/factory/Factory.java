package factory;

import factory.product.Accessory;
import factory.product.Body;
import factory.product.Engine;
import factory.storage.Storage;

public class Factory {
    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;

    public Factory(int bodyStorageSize, int engineStorageSize, int accessoryStorageSize) {
        this.bodyStorage = new Storage<>(bodyStorageSize);
        this.engineStorage = new Storage<>(engineStorageSize);
        this.accessoryStorage = new Storage<>(accessoryStorageSize);
    }
}