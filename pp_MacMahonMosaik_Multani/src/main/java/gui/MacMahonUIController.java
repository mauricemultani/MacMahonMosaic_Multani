package gui;

import gui.controllers.GameFieldController;
import gui.controllers.GridBottomController;
import gui.controllers.GridLeftController;
import gui.controllers.GridRightController;
import gui.management.Management;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.mosaicgame.Game;

/**
 * Main class for the user interface.
 *
 * @author mjo, cei
 */
public class MacMahonUIController implements GridLeftController, GridRightController, GridBottomController, GameFieldController {

    private Management management;

    private Game game;

    @FXML
    private GridPane gameField;
    @FXML
    private Pane gameFieldPane;

    @FXML
    private GridPane gridLeft;
    @FXML
    private GridPane gridRight;
    @FXML
    private GridPane gridBottom;



    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     */
    public void initialize() {
        //Anpassung des Spielfeldes
        adjustRowsAndColumnsGameField(gameField);
        adjustGameField(gameField, gameFieldPane.getWidth(), gameFieldPane.getHeight());

        gameFieldPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustGameField(gameField, newVal.doubleValue(), gameFieldPane.getHeight());
        });

        gameFieldPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            adjustGameField(gameField, gameFieldPane.getWidth(), newVal.doubleValue());
        });

        //Initialisierung des Beginn des Spiels
        game = new Game(gameField);


        // Initialisierung der Bilder in den GridPanes
        initImagesGameField(gameField);
        initImagesOnBorderGameField(gameField);
        initImagesLeft(gridLeft);
        initImagesRight(gridRight);
        initImagesBottom(gridBottom);
    }

}