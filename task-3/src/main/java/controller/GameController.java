package controller;

import model.Card;
import model.Game;
import view.GameView;

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

    public void updateView() {
    }

    public void playCard(Card card) {
    }

}