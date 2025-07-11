package gui;

import gui.controller.BoardController;
import gui.controller.EditorController;
import gui.controller.GridBottomController;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Board;
import logic.GUIConnector;
import logic.Game;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Main class for the user interface.
 * <p>
 * Die Methoden unter initialize() gehören zur MenuBar welche dann Aktionen auslösen.
 *
 * @author mjo, cei, Maurice Singh Multani
 */
public class MacMahonUIController {

    private final GUIConnector gui = new JavaFXGUI();

    private Game game;

    private BoardController boardController;

    private GridBottomController gridBottomController;

    private EditorController editorController;

    private boolean success;

    /**
     * das Spielfeld, die zugehörige Pane und
     * die Anzeige der benutzbaren Bilder via einer GridPane darunter.
     */
    @FXML
    private GridPane gameField;
    @FXML
    private Pane gameFieldPane;
    @FXML
    private GridPane gridBottom;
    @FXML
    private CheckMenuItem menuEditorMode;

    /**
     * This is where you need to add code that should happen during
     * initialization and then change the java doc comment.
     */
    public void initialize() {
        // Anzahl an Reihen
        int rows = 7;

        // Anzahl an Spalten
        int columns = 7;

        // Konstruktor, welches die Zeilen und Spalten aufnimmt
        // erstellt auch Löcher und initialisiert Randfarben.
        Board board = new Board(rows, columns, true);

        // Initialisierung der Controller für Spielfeld und gridBottom
        this.boardController = new BoardController(gameField, board, gameFieldPane);
        this.gridBottomController = new GridBottomController(gridBottom, board);

        // Initialisiert das Spielfeld
        boardController.initializeBoard();

        // Erstellung eines neuen Spiels
        game = new Game(board);

        // Initialisierung des Controllers für den Editor Modus
        this.editorController = new EditorController(gameField, board, gameFieldPane, boardController, gui, game, gridBottomController);
        menuEditorMode.selectedProperty().addListener((obs, notSelected, isSelected) -> {
            handleEditorMode();
        });

        // Initialisiert die Bilder im gridBottom.
        // Drag-Logik ist auch drin.
        gridBottomController.initImages();

        closeGameViaStage();
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
            gui.showSuccessSave();
        } else if (!success){
            gui.showFailSave();
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

            boardController.setBoardAndUpdate(game.getBoard());
            gridBottomController.setBoard(game.getBoard());
            gridBottomController.checkExistentMosaikTiles();

            success = true;
            gui.showSuccessLoad();
        } else if (!success) {
            gui.showFailLoad();
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
        Stage stage = (Stage) gameField.getScene().getWindow();
        Optional<ButtonType> result = gui.showSignWhenHandleClose();

        if (result.isPresent()) {
            if (result.get().getText().equals("Save")) {
                handleSave();
                stage.close();
            } else if (result.get().getText().equals("Close the Game")) {
                stage.close();
            }
        }
    }

    /**
     * Startet und schließt den Editor-Modus.
     * <p>
     * Mögliche Dinge die zu beachten sind:
     * Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     *
     * 1. Wenn laufendes Spiel keinen Namen hat, ob dieser unter einem Namen gespeichert werden soll.
     * 2. Editor-Modus kann nur geschlossen werden, wenn das Puzzle gespielt werden kann
     * (siehe Editor-class, die Methode switchBackToGameMode())
     */
    @FXML
    private void handleEditorMode() {
        if (menuEditorMode.isSelected()) {
                editorController.initializeEditorMode();
            } else {
                if (editorController.canSwitchBackToGameMode()) {
                    editorController.switchBackToGameMode();
                } else {
                    menuEditorMode.setSelected(true);
                }
            }
    }

    /**
     * Mit diesem handle kann die Größe des Spielfeldes verändert werden.
     */
    @FXML
    private void handleChangeSizeOfField() {
        if (menuEditorMode.isSelected()) {
            editorController.changeSizeOfGameField();
        } else {
            gui.showEditorNotActive();
        }
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

    /**
     * Die Methode soll darauf reagieren, wenn der Benutzer
     * das Spiel mit dem 'X' oben rechts am Fenster schließen will.
     */
    private void closeGameViaStage() {
        gameField.sceneProperty().addListener(((observableValue, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        Stage stage = (Stage) newWindow;
                        stage.setOnCloseRequest(windowEvent -> {
                            Optional<ButtonType> result = gui.showSignWhenHandleClose();

                            if (result.isPresent()) {
                                if (result.get().getText().equals("Save")) {
                                    handleSave();
                                    stage.close();
                                } else if (result.get().getText().equals("Close the Game")) {
                                    stage.close();
                                } else {
                                    windowEvent.consume();
                                }
                            } else {
                                windowEvent.consume();
                            }
                        });
                    }
                });
            }
        }));
    }
}