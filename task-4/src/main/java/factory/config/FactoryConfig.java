package factory.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfig {
    private final Properties properties;

    public FactoryConfig(String configPath) throws IOException {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configPath)) {
            if (input == null) {
                throw new IOException("Config file not found: " + configPath);
            }
            properties.load(input);
        }
    }

    public int getStorageBodySize() {
        return Integer.parseInt(properties.getProperty("StorageBodySize"));
    }

    public int getStorageEngineSize() {
        return Integer.parseInt(properties.getProperty("StorageEngineSize"));
    }

    public int getStorageAccessorySize() {
        return Integer.parseInt(properties.getProperty("StorageAccessorySize"));
    }

    public int getStorageAutoSize() {
        return Integer.parseInt(properties.getProperty("StorageAutoSize"));
    }

    public int getAccessorySuppliersCount() {
        return Integer.parseInt(properties.getProperty("AccessorySuppliers"));
    }

    public int getWorkersCount() {
        return Integer.parseInt(properties.getProperty("Workers"));
    }

    public int getDealersCount() {
        return Integer.parseInt(properties.getProperty("Dealers"));
    }

    public boolean isLogSale() {
        return Boolean.parseBoolean(properties.getProperty("LogSale"));
    }
}