package factory.config;

import java.io.FileInputStream;
import java.util.Properties;

public class FactoryConfig {
    private final int bodyStorageSize;
    private final int engineStorageSize;
    private final int detailStorageSize;
    private final int carStorageSize;
    private final int numAccessorySuppliers;
    private final int numWorkers;
    private final int numDealers;
    private final boolean logSale;

    public FactoryConfig(String configFile) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(configFile));
        this.bodyStorageSize = Integer.parseInt(props.getProperty("StorageBodySize", "100"));
        this.engineStorageSize = Integer.parseInt(props.getProperty("StorageMotorSize", "100"));
        this.detailStorageSize = Integer.parseInt(props.getProperty("StorageAccessorySize", "100"));
        this.carStorageSize = Integer.parseInt(props.getProperty("StorageAutoSize", "100"));
        this.numAccessorySuppliers = Integer.parseInt(props.getProperty("AccessorySuppliers", "5"));
        this.numWorkers = Integer.parseInt(props.getProperty("Workers", "10"));
        this.numDealers = Integer.parseInt(props.getProperty("Dealers", "20"));
        this.logSale = Boolean.parseBoolean(props.getProperty("LogSale", "true"));
    }

    public int getBodyStorageSize() {
        return bodyStorageSize;
    }

    public int getEngineStorageSize() {
        return engineStorageSize;
    }

    public int getDetailStorageSize() {
        return detailStorageSize;
    }

    public int getCarStorageSize() {
        return carStorageSize;
    }

    public int getNumAccessorySuppliers() {
        return numAccessorySuppliers;
    }

    public int getNumWorkers() {
        return numWorkers;
    }

    public int getNumDealers() {
        return numDealers;
    }

    public boolean isLogSale() {
        return logSale;
    }
}