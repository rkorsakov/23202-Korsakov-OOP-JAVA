package factory.gui;

import factory.Factory;
import factory.supplier.Supplier;

import javax.swing.*;
import java.awt.*;

public class FactoryGUI extends JFrame {
    private final Factory factory;

    // Компоненты интерфейса
    private JLabel bodyStockLabel, engineStockLabel, accessoryStockLabel, carStockLabel;
    private JLabel bodyProducedLabel, engineProducedLabel, accessoryProducedLabel, carsProducedLabel;
    private JLabel tasksPendingLabel;
    private JSlider bodySpeedSlider, engineSpeedSlider, accessorySpeedSlider, dealerSpeedSlider;

    public FactoryGUI(Factory factory) {
        this.factory = factory;
        initUI();
        startUpdating();
    }

    private void initUI() {
        setTitle("Factory Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        setSize(800, 600);

        add(new JLabel("Body Stock:"));
        bodyStockLabel = new JLabel("0");
        add(bodyStockLabel);

        add(new JLabel("Engine Stock:"));
        engineStockLabel = new JLabel("0");
        add(engineStockLabel);

        add(new JLabel("Accessory Stock:"));
        accessoryStockLabel = new JLabel("0");
        add(accessoryStockLabel);

        add(new JLabel("Car Stock:"));
        carStockLabel = new JLabel("0");
        add(carStockLabel);

        add(new JLabel("Bodies Produced:"));
        bodyProducedLabel = new JLabel("0");
        add(bodyProducedLabel);

        add(new JLabel("Engines Produced:"));
        engineProducedLabel = new JLabel("0");
        add(engineProducedLabel);

        add(new JLabel("Accessories Produced:"));
        accessoryProducedLabel = new JLabel("0");
        add(accessoryProducedLabel);

        add(new JLabel("Cars Produced:"));
        carsProducedLabel = new JLabel("0");
        add(carsProducedLabel);


        add(new JLabel("Tasks Pending:"));
        tasksPendingLabel = new JLabel("0");
        add(tasksPendingLabel);
        add(new JLabel("Body Supplier Delay (ms):"));
        bodySpeedSlider = new JSlider(500, 5000, 1500);
        bodySpeedSlider.setMajorTickSpacing(1000);
        bodySpeedSlider.setMinorTickSpacing(250);
        bodySpeedSlider.setPaintTicks(true);
        bodySpeedSlider.setPaintLabels(true);
        bodySpeedSlider.addChangeListener(e -> factory.getBodySupplier().setProductionTime(bodySpeedSlider.getValue()));
        add(bodySpeedSlider);

        add(new JLabel("Engine Supplier Delay (ms):"));
        engineSpeedSlider = new JSlider(500, 5000, 2000);
        engineSpeedSlider.setMajorTickSpacing(1000);
        engineSpeedSlider.setMinorTickSpacing(250);
        engineSpeedSlider.setPaintTicks(true);
        engineSpeedSlider.setPaintLabels(true);
        engineSpeedSlider.addChangeListener(e -> factory.getEngineSupplier().setProductionTime(engineSpeedSlider.getValue()));
        add(engineSpeedSlider);

        add(new JLabel("Accessory Supplier Delay (ms):"));
        accessorySpeedSlider = new JSlider(500, 5000, 500);
        accessorySpeedSlider.setMajorTickSpacing(1000);
        accessorySpeedSlider.setMinorTickSpacing(250);
        accessorySpeedSlider.setPaintTicks(true);
        accessorySpeedSlider.setPaintLabels(true);
        accessorySpeedSlider.addChangeListener(e -> {
            int value = accessorySpeedSlider.getValue();
            factory.getAccessorySuppliers().forEach(supplier -> supplier.setProductionTime(value));
        });
        add(accessorySpeedSlider);

        add(new JLabel("Dealer Delay (ms):"));
        dealerSpeedSlider = new JSlider(500, 5000, 1000);
        dealerSpeedSlider.setMajorTickSpacing(1000);
        dealerSpeedSlider.setMinorTickSpacing(250);
        dealerSpeedSlider.setPaintTicks(true);
        dealerSpeedSlider.setPaintLabels(true);
        dealerSpeedSlider.addChangeListener(e -> {
            int value = dealerSpeedSlider.getValue();
            factory.getDealers().forEach(dealer -> dealer.setDelay(value));
        });
        add(dealerSpeedSlider);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                factory.stop();
                System.exit(0);
            }
        });
    }

    private void startUpdating() {
        new Thread(() -> {
            while (true) {
                updateUI();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    private void updateUI() {
        bodyStockLabel.setText(String.valueOf(factory.getBodyStorage().getSize()));
        engineStockLabel.setText(String.valueOf(factory.getEngineStorage().getSize()));
        accessoryStockLabel.setText(String.valueOf(factory.getAccessoryStorage().getSize()));
        carStockLabel.setText(String.valueOf(factory.getCarStorage().getSize()));
        bodyProducedLabel.setText(String.valueOf(factory.getBodySupplier().getProducedCount()));
        engineProducedLabel.setText(String.valueOf(factory.getEngineSupplier().getProducedCount()));
        int totalAccessories = factory.getAccessorySuppliers().stream()
                .mapToInt(Supplier::getProducedCount)
                .sum();
        accessoryProducedLabel.setText(String.valueOf(totalAccessories));
        carsProducedLabel.setText(String.valueOf(factory.getCarsProduced()));
        tasksPendingLabel.setText(String.valueOf(factory.getThreadPool().getTaskQueueSize()));
    }
}