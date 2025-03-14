package controller;

import model.Card;
import model.Game;
import view.GameView;

import java.util.ArrayList;

public class GameController {
    private final Game game;
    private GameView view;

    public GameController(int playersCount, GameView view) {
        this.game = new Game(playersCount);
        this.view = view;
    }

    public void startGame() {
        System.out.println("Game started. Trump: " + game.getTrumpSuit());
        updateView();
    }

    public void playHand(ArrayList<Card> selectedCards) {
        try {
            updateView();
        } catch (Exception e) {
            view.printError(e.getMessage());
        }
    }

    public void updateView() {
        if (view != null) {
            view.updateHand(game.getCurrentPlayer().getHand());
        }
    }

    public void setView(GameView view) {
        this.view = view;
    }
}