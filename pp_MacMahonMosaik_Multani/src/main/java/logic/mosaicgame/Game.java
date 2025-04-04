package logic.mosaicgame;

import gui.management.Management;
import javafx.scene.layout.GridPane;

public class Game extends Management {

    private GridPane gameField;

    private String[] ongoingGame;

    public Game(GridPane gameField){
        this.gameField = gameField;
        initializeGame();
    }

    /**
     * Konstruktor für Testzwecke. Erzeugt ein befindliches Spiel mitten im Spielgeschehen.
     * Nimmt das Spielfeld in Form eines StringArrays entgegen.
     * @param gridPane
     * @param ongoingGame
     */
    public Game(GridPane gridPane, String[] ongoingGame){
        this.gameField = gameField;
        this.ongoingGame = ongoingGame;
    }

    /**
     * Startet ein neues Spiel
     */
    public void initializeGame() {
        clearGameField();
    }

    public void clearGameField() {
        gameField.getChildren().clear();
    }
}
