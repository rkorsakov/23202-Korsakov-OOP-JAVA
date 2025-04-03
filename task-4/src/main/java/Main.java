import factory.Factory;
import factory.config.FactoryConfig;

public class Main {
    public static void main(String[] args) {
        try {
            FactoryConfig config = new FactoryConfig("factory_config.properties");
            Factory factory = new Factory(config);
            factory.start();
            Thread.sleep(2_000);
            factory.stop();
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}