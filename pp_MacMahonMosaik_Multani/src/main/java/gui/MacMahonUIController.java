package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import logic.*;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Main class for the user interface.
 * <p>
 * Die Methoden unter initialize() gehören zur MenuBar welche dann Aktionen auslösen.
 *
 * @author mjo, cei, Maurice Singh Multani
 */
public class MacMahonUIController {

    private final JavaFXGUI gui = new JavaFXGUI();

    private Game game;

    private MosaicTile tile;

    private Rotation rotation;

    private boolean success;

    private BoardCell[][] ongoingGame;

    /**
     * das Spielfeld, die zugehörige Pane dazu
     * und die Anzeige der benutzbaren Bilder via einer GridPane darunter.
     */
    @FXML
    private GridPane gameField;
    @FXML
    private Pane gameFieldPane;
    @FXML
    private GridPane gridBottom;

    /**
     * MenuItems, welche die Datei speichern, speichern unter und schließen
     */
    @FXML
    private MenuItem menuFileSave;
    @FXML
    private MenuItem menuFileLoad;
    @FXML
    private MenuItem menuFileClose;

    /**
     * MenuItems, welche den Editor aktivieren, bei Aktivierung
     * die Größe des Feldes ändern lassen und deaktivieren.
     */
    @FXML
    private MenuItem menuEditorActivate;
    @FXML
    private MenuItem menuEditorChangeSize;

    /**
     * MenuItems, welche das Spiel einreichen, neu starten
     * und dem Spieler ein Hinweis geben
     */
    @FXML
    private MenuItem menuGameSolution;
    @FXML
    private MenuItem menuGameRestart;
    @FXML
    private MenuItem menuGameHelp;

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     */
    public void initialize() {
        // Anzahl an Reihen
        int rows = 8;

        // Anzahl an Spalten
        int columns = 8;

        // Konstruktor, welches die Zeilen und Spalten aufnimmt
        // erstellt auch Löcher und initialisiert Randfarben.
        Board board = new Board(rows, columns);

        // Initialisierung der Controller für Spielfeld und GridPanes
        BoardController boardController = new BoardController(gameField, board, gameFieldPane);
        GridBottomController gridBottomController = new GridBottomController(gridBottom);

        // Initialisiert das Spielfeld
        boardController.initializeBoard();

        // Erstellung eines neuen Spiels
        game = new Game(board);

        // Initialisiert die Bilder im gridBottom.
        // Drag-Logik ist auch drin.
        gridBottomController.initImages();
    }

    /**
     * Speichert das laufende Spiel unter einen Namen.
     */
    @FXML
    private void handleSave() {
        FileChooser fileChooser = getFileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(".json", "*.json")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            game.saveGame(file);
            success = true;
            gui.showSuccess();
        } else if (!success){
            gui.showFail();
        }
    }

    /**
     * Lädt ein gespeichertes Spiel.
     * Beachten von Fehlermeldungen, wenn kein gespeichertes Spiel geladen wird.
     */
    @FXML
    private void handleLoad() {
        FileChooser fileChooser = getFileChooser();

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            game.loadGame(file);
            success = true;
        } else if (!success) {
            gui.showFail();
        }
    }

    /**
     * Private Methode welche ein FileChooser erstellt, um ein Spiel zu speichern.
     *
     * @return FileChooser zum Speichern von Dateien.
     */
    private FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("MacMahonMosaik");
        fileChooser.setInitialFileName("*.json");

        File currDir;
        try {
            currDir = new File(
                    MacMahonUIController.class.getProtectionDomain().getCodeSource()
                            .getLocation().toURI());
        } catch (URISyntaxException ex) {
            throw new IllegalArgumentException(ex);
        }

        File saveFile = new File(String.valueOf(currDir));

        fileChooser.setInitialDirectory(saveFile);
        return fileChooser;
    }

    /**
     * Schließt das laufende Spiel.
     * <p>
     * Mögliche Dinge die zu beachten sind:
     * - Ob das Spiel unter einen Namen gespeichert werden soll.
     */
    @FXML
    private void handleClose() {

    }

    /**
     * Startet den Editor-Modus.
     * <p>
     * Mögliche Dinge die zu beachten sind:
     * Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     */
    @FXML
    private void handleEditorActivate() {

    }

    /**
     * Schließt den Editor-Modus.
     * <p>
     * Mögliche Dinge die zu beachten sind:
     * 1. Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     * 2. Editor-Modus kann nur geschlossen werden, wenn das Puzzle gespielt werden kann
     * (siehe Editor-class, die Methode switchBackToGameMode())
     */
    @FXML
    private void handleEditorDeactivate() {

    }

    /**
     * Reicht das laufende Spiel ein.
     */
    @FXML
    private void handleGameSubmit() {

    }

    /**
     * Startet das laufende Spiel neu.
     * <p>
     * Mögliche Dinge die zu beachten sind:
     * Ob der Spieler nicht vielleicht, das laufende Spiel
     * speichern und einen Namen geben will.
     */
    @FXML
    private void handleGameRestart() {

    }

    /**
     * Gibt dem Spieler ein Hinweis, um das Spiel zu lösen.
     * <p>
     * Zu Beachten: unendliche Nutzung möglich.
     */
    @FXML
    private void handleGameHint() {

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