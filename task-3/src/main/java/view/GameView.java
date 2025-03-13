package view;

import model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class GameView {
    private JFrame frame;
    private JPanel handPanel;
    private JPanel tablePanel;
    private JButton makeMoveButton;
    private Consumer<ArrayList<Card>> onHandSelected;
    private Set<Card> selectedCards;

    public GameView(Consumer<ArrayList<Card>> onHandSelected) {
        this.onHandSelected = onHandSelected;
        this.selectedCards = new HashSet<>();
        SwingUtilities.invokeLater(this::initializeUI);
    }

    private void initializeUI() {
        frame = new JFrame("DURAK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        handPanel = new JPanel();
        tablePanel = new JPanel();

        makeMoveButton = new JButton("PLAY");
        makeMoveButton.addActionListener(e -> {
            if (onHandSelected != null && !selectedCards.isEmpty()) {
                onHandSelected.accept(new ArrayList<>(selectedCards));
                selectedCards.clear();
                updateHandDisplay();
            }
        });
        makeMoveButton.setEnabled(false);

        JPanel controlPanel = new JPanel();
        controlPanel.add(makeMoveButton);

        frame.add(handPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.NORTH);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void updateHandDisplay() {
        if (handPanel != null) {
            for (Component comp : handPanel.getComponents()) {
                if (comp instanceof JButton) {
                    ((JButton) comp).setBackground(null);
                }
            }
            handPanel.revalidate();
            handPanel.repaint();
        }
    }

    public void updateHand(ArrayList<Card> hand) {
        if (handPanel == null) {
            throw new IllegalStateException("handPanel is not initialized yet!");
        }
        handPanel.removeAll();
        selectedCards.clear();
        for (Card card : hand) {
            JButton cardButton = new JButton(card.toString());
            cardButton.addActionListener(e -> {
                if (selectedCards.contains(card)) {
                    selectedCards.remove(card);
                    cardButton.setBackground(null);
                } else {
                    selectedCards.add(card);
                    cardButton.setBackground(Color.LIGHT_GRAY);
                }
                makeMoveButton.setEnabled(!selectedCards.isEmpty());
            });
            handPanel.add(cardButton);
        }
        makeMoveButton.setEnabled(false);
        handPanel.revalidate();
        handPanel.repaint();
    }

    public void updateTable(ArrayList<Card> table) {
        if (tablePanel == null) {
            throw new IllegalStateException("tablePanel is not initialized yet!");
        }
        tablePanel.removeAll();
        for (Card card : table) {
            JLabel cardLabel = new JLabel(card.toString());
            tablePanel.add(cardLabel);
        }
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}