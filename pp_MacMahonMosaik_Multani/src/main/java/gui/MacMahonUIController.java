package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.Game;

/**
 * Main class for the user interface.
 *
 * Die Methoden unter initialize() gehören zur MenuBar welche dann Aktionen auslösen.
 *
 *
 * @author mjo, cei, Maurice Singh Multani
 */
public class MacMahonUIController {

    private Game game;

    // GridPane für das Spielfeld
    @FXML
    private GridPane gameField;

    // Pane, die das Spielfeld enthält (Wichtig für die Größenverhältnisse).
    @FXML
    private Pane gameFieldPane;

    // GridPane für die Anzeige der Bilder auf der unteren Seite.
    @FXML
    private GridPane gridBottom;

    // MenuItems, welche die Datei speichern, speichern unter und schließen
    @FXML
    private MenuItem menuFileSave;
    @FXML
    private MenuItem menuFileLoad;
    @FXML
    private MenuItem menuFileClose;

    // MenuItems, welche den Editor aktivieren, bei aktivierung die Größe des Feldes ändern lassen und deaktivieren.
    @FXML
    private MenuItem menuEditorActivate;
    @FXML
    private MenuItem menuEditorChangeSize;

    // MenuItems, welche das Spiel einreichen, neu starten
    // und dem Spieler ein Hinweis geben
    @FXML
    private MenuItem menuGameSolution;
    @FXML
    private MenuItem menuGameRestart;
    @FXML
    private MenuItem menuGameHelp;

    private BoardController gameFieldController;

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     */
    public void initialize() {

        // Initialisierung der Controller für Spielfeld und GridPanes
        gameFieldController = new BoardController(gameField);
        GridBottomController gridBottomController = new GridBottomController(gridBottom);

        // Erstellung eines neuen Spiels
        // game = new Game();

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
        // gameFieldController.initImagesGameField();
        gameFieldController.initImagesBorderGameField();
        gridBottomController.initImages();

        gameFieldController.dropTiles();

    }

    /**
     * Speichert das laufende Spiel.
     *
     * Beachten, dass das Spiel vorher einen Namen hat.
     */
    @FXML
    private void handleSave(){

    }

    /**
     * Speichert das laufende Spiel unter einen Namen.
     */
    @FXML
    private void handleSaveAs(){

    }

    /**
     * Schließt das laufende Spiel.
     *
     * Mögliche Dinge die zu beachten sind:
     * - Ob das Spiel unter einen Namen gespeichert werden soll.
     */
    @FXML
    private void handleClose(){

    }

    /**
     * Startet den Editor-Modus.
     *
     * Mögliche Dinge die zu beachten sind:
     * Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     */
    @FXML
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
    @FXML
    private void handleEditorDeactivate(){

    }

    /**
     * Reicht das laufende Spiel ein.
     *
     */
    @FXML
    private void handleGameSubmit(){

    }

    /**
     * Startet das laufende Spiel neu.
     *
     * Mögliche Dinge die zu beachten sind:
     * Ob der Spieler nicht vielleicht, das laufende Spiel
     * speichern und einen Namen geben will.
     */
    @FXML
    private void handleGameRestart(){

    }

    /**
     * Gibt dem Spieler ein Hinweis, um das Spiel zu lösen.
     *
     * Zu Beachten: unendliche Nutzung möglich.
     */
    @FXML
    private void handleGameHint(){

    }

    @FXML
    private void handleRotateButton(ActionEvent actionEvent) {

    }

//    /**
//     * Reagiert auf Änderungen des Berechnungstyps vom Nutzer.
//     * Wenn der Benutzer einen anderen Berechnungstyp auswählt oder direkt eine neue Runde startet,
//     * wird eine neue Runde gestartet.
//     */
//    @FXML
//    private void handleCalcTypeChanged() {
//        RadioMenuItem selectedMenu = (RadioMenuItem) tglGrpCalcType.getSelectedToggle();
//        CalculationType selectedType = CalculationType.valueOf(selectedMenu.getText().toUpperCase());
//
//        MathProblem.operator = selectedType;
//
//        this.logic = new Logic(new JavaFXGUI(this.lblProgress, this.lblProblem, this.lblPrevProblems), selectedType);
//
//        lblPrevProblems.setText("");
//        lblProgress.setText("");
//        txtFldProblem.clear();
//        hBxProblem.setDisable(false);
//
//        this.logic.solve(null);
//    }
}