import factory.Factory;
import factory.config.FactoryConfig;
import factory.gui.FactoryGUI;

public class Main {
    public static void main(String[] args) {
        try {
            FactoryConfig config = new FactoryConfig("factory_config.properties");
            Factory factory = new Factory(config);
            FactoryGUI gui = new FactoryGUI(factory);
            factory.start();
            gui.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}