package gui;

import gui.gridControllers.GridBottomController;
import gui.gridControllers.GridLeftController;
import gui.gridControllers.GridRightController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.Game;
import logic.Solvability;

/**
 * Main class for the user interface.
 *
 * Die Methoden unter initialize() gehören zur MenuBar welche dann Aktionen auslösen.
 *
 *
 * @author mjo, cei, Maurice Singh Multani
 */
public class MacMahonUIController implements Solvability {

    private Game game;

    // GridPane für das Spielfeld
    @FXML
    private GridPane gameField;

    // Pane, die das Spielfeld enthält (Wichtig für die Größenverhältnisse).
    @FXML
    private Pane gameFieldPane;

    // GridPane für die Anzeige der Bilder auf der linken, rechten und unteren Seite.
    // Die Seite ist zu erkennen anhand der Benennung
    @FXML
    private GridPane gridLeft;
    @FXML
    private GridPane gridRight;
    @FXML
    private GridPane gridBottom;

    // MenuItems, welche die Datei speichern, speichern unter und schließen
    @FXML
    private MenuItem menuFileSave;
    @FXML
    private MenuItem menuFileSaveAs;
    @FXML
    private MenuItem menuFileClose;

    // MenuItems, welche den Editor aktivieren, bei aktivierung die Größe des Feldes ändern lassen und deaktivieren.
    @FXML
    private MenuItem menuEditorActivate;
    @FXML
    private MenuItem menuEditorDeactivate;
    @FXML
    private MenuItem menuEditorChangeSize;

    // MenuItems, welche das Spiel einreichen, neu starten
    // und dem Spieler ein Hinweis geben
    @FXML
    private MenuItem menuGameSubmit;
    @FXML
    private MenuItem menuGameRestart;
    @FXML
    private MenuItem menuGameHint;

    private GameFieldController gameFieldController;

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     */
    public void initialize() {

        // Initialisierung der Controller für Spielfeld und GridPanes
        gameFieldController = new GameFieldController(gameField);
        GridLeftController gridLeftController = new GridLeftController(gridLeft);
        GridRightController gridRightController = new GridRightController(gridRight);
        GridBottomController gridBottomController = new GridBottomController(gridBottom);

        // Erstellung eines neuen Spiels
        game = new Game();

        // Anpassung Zeilen und Spalten des Spielfelds
        gameFieldController.adjustRowsAndColumnsGameField();
        gameFieldController.adjustGameField(gameFieldPane.getWidth(), gameField.getHeight());

        // Anpassung des Spielfeldes bei Größenveränderung
        // Spielfeld bleibt quadratisch
        gameFieldPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            gameFieldController.adjustGameField(newVal.doubleValue(), gameFieldPane.getHeight());
        });
        gameFieldPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gameFieldController.adjustGameField(gameFieldPane.getWidth(), newVal.doubleValue());
        });




        // Initialisierung der Bilder in den GridPanes
        gameFieldController.initImagesGameField();
        gameFieldController.randomColorsForBorder();
        gridLeftController.initImages();
        gridRightController.initImages();
        gridBottomController.initImages();
    }

    /**
     * Speichert das laufende Spiel.
     *
     * Beachten, dass das Spiel vorher einen Namen hat.
     */
    private void handleSave(){

    }

    /**
     * Speichert das laufende Spiel unter einen Namen.
     */
    private void handleSaveAs(){

    }

    /**
     * Schließt das laufende Spiel.
     *
     * Mögliche Dinge die zu beachten sind:
     * - Ob das Spiel unter einen Namen gespeichert werden soll.
     */
    private void handleClose(){

    }

    /**
     * Startet den Editor-Modus.
     *
     * Mögliche Dinge die zu beachten sind:
     * Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     */
    private void handleEditorActivate(){

    }

    /**
     * Schließt den Editor-Modus.
     *
     * Mögliche Dinge die zu beachten sind:
     * 1. Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     * 2. Editor-Modus kann nur geschlossen werden, wenn das Puzzle gespielt werden kann
     *  (siehe Editor-class, die Methode switchBackToGameMode())
     */
    private void handleEditorDeactivate(){

    }

    /**
     * Reicht das laufende Spiel ein.
     *
     */
    private void handleGameSubmit(){

    }

    /**
     * Startet das laufende Spiel neu.
     *
     * Mögliche Dinge die zu beachten sind:
     * Ob der Spieler nicht vielleicht, das laufende Spiel
     * speichern und einen Namen geben will.
     */
    private void handleGameRestart(){

    }

    /**
     * Gibt dem Spieler ein Hinweis, um das Spiel zu lösen.
     *
     * Zu Beachten: unendliche Nutzung möglich.
     */
    private void handleGameHint(){

    }

}