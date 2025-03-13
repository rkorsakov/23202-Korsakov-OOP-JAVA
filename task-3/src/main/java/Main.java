import controller.GameController;
import view.GameView;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController(2, null);
        GameView view = new GameView(controller::playHand);
        controller.setView(view);
        SwingUtilities.invokeLater(controller::startGame);
    }
}