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
import logic.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Random;

/**
 * Main class for the user interface.
 * <p>
 * Die Methoden unter initialize() gehören zur MenuBar welche dann Aktionen auslösen.
 *
 * @author mjo, cei, Maurice Singh Multani
 */
public class MacMahonUIController {

    private final GUIConnector gui = new JavaFXGUI();

    private BoardOptions options;

    private Editor editor;

    private BoardController boardController;

    private GridBottomController gridBottomController;

    private EditorController editorController;

    private Solvability solve;

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
        Random random = new Random();

        // Anzahl an Reihen kann bis zu 4 - mit Bound = 5 haben (mit +2 garantiert es min. 2)
        int rows = random.nextInt(5) + 2;

        // Anzahl an Spalten kann bis zu 4 - mit Bound = 5 haben (mit +2 garantiert es min. 2)
        int columns = random.nextInt(5) + 2;

        // Konstruktor, welches die Zeilen und Spalten aufnimmt
        // erstellt auch Löcher und initialisiert Randfarben.
        // +2 stehen für die Ränder
        Board board = new Board(rows + 2, columns + 2, true);

        // Erstellung von Optionen
        options = new BoardOptions(board);

        // Initialisiert die Logik für den Editor
        editor = new Editor(board);

        //Initialisieren des Konstruktors für Solvability
        solve = new Solvability(board, editor, options);

        // Initialisierung der Controller für Spielfeld und gridBottom
        this.boardController = new BoardController(gameField, board, gameFieldPane, gui, solve);
        this.gridBottomController = new GridBottomController(gridBottom, board);

        // Initialisiert das Spielfeld
        boardController.initializeBoard();

        // Initialisierung des Controllers für den Editor Modus
        this.editorController = new EditorController(gameField, board, gameFieldPane,
                boardController, gui, options, gridBottomController, editor, solve);

        // Initialisiert die Bilder im gridBottom.
        // Drag-Logik ist auch drin.
        gridBottomController.checkExistentMosaikTiles();

        closeGameViaStage();
    }

    /**
     * Speichert das laufende Spiel unter einen Namen.
     */
    @FXML
    private void handleSave() {
        if (!editor.needsHoles(editor.getBoard().getRows(), editor.getBoard().getColumns())) {
            FileChooser fileChooser = getFileChooser();

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(".json", "*.json")
            );
            File file = fileChooser.showSaveDialog(null);

            if (file != null && file.getName().endsWith(".json")) {
                options.saveGame(file);
                success = true;
                gui.showSuccessSave();
            } else if (!success) {
                gui.showFailSave();
            }
        } else {
            gui.showHolesNotPlaced_Save();
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

        if (menuEditorMode.isSelected() && file != null && file.getName().endsWith(".json")) {
            Board clonedBoard = options.cloneBoard(options.getBoard());

            try {
                editor.loadGame(file);

                options.setBoard(editor.getBoard());
                boardController.setBoardAndUpdate(options.getBoard());
                gridBottomController.setBoard(options.getBoard());
                gridBottomController.checkExistentMosaikTiles();

                success = true;
                gui.showSuccessLoad();
                editorController.initializeEditorMode();

                checkClonedBoard(clonedBoard);

                options.setBoard(options.getBoard());

            } catch (Exception e) {
                gui.showFailLoad();
            }
        } else if (file != null && file.getName().endsWith(".json")) {
            Board clonedBoard = options.cloneBoard(options.getBoard());

            try {
                options.loadGame(file);

                boardController.setBoardAndUpdate(options.getBoard());
                boardController.initializeBoard();

                gridBottomController.setBoard(options.getBoard());
                gridBottomController.checkExistentMosaikTiles();

                success = true;
                gui.showSuccessLoad();

                checkClonedBoard(clonedBoard);

                options.setBoard(options.getBoard());

            } catch (Exception e) {
                gui.showFailLoad();
            }
        } else {
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
        } else if (editorController.canSwitchBackToGameMode()) {
            menuEditorMode.setSelected(false);
            editorController.switchBackToGameMode();
        } else {
            menuEditorMode.setSelected(true);
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
        Board currBoard = options.getBoard();
        Solvability solve = new Solvability(currBoard, editor, options);

        if (!menuEditorMode.isSelected()) {
            if (!solve.allCellsPlaced(currBoard)) {
                gui.showPlaceAllTilesFirst();
            } else {
                success = solve.solveGame();

                if (success) {
                    gui.showGameWon();
                } else {
                    gui.showGamesNotFinished();
                }
            }
        } else {
            gui.showNotAvailableInEditor();
        }
    }

    /**
     * Startet das laufende Spiel neu.
     */
    @FXML
    private void handleGameRestart() {
        boardController.restartGame();

        Board newBoard = this.options.getBoard();

        options.setBoard(newBoard);

        boardController.setBoardAndUpdate(newBoard);
        gridBottomController.setBoard(newBoard);
        gridBottomController.checkExistentMosaikTiles();
    }

    /**
     * Platziert für den Spieler ein Mosaikteil
     * an einer beliebigen Stelle.
     *
     * Zu Beachten: unendliche Nutzung möglich.
     */
    @FXML
    private void handleGameHint() {
        if (!menuEditorMode.isSelected()) {
            if (!solve.overEighteenEmptyCells()) {
                boardController.placingTileForPlayer();

                gridBottomController.setBoard(options.getBoard());
                gridBottomController.checkExistentMosaikTiles();
            } else {
                gui.showSkipHelp();
            }
        } else {
            gui.showNotAvailableInEditor();
        }
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

    /**
     * Die private Hilfsmethode soll redundanten Code in handleLoad verringern,
     * damit handleLoad übersichtlich bleibt.
     */
    private void checkClonedBoard(Board clonedBoard) {
        // Überprüfung, ob das geladene Spiel lösbar ist
        if (!solve.overEighteenEmptyCells()) {
            // wenn nicht, soll es das vorherige Spiel wieder laden
            if (!solve.possibleSolvation()) {
                gui.showNoPossibleSolvation();

                boardController.setBoardAndUpdate(clonedBoard);
                boardController.initializeBoard();

                gridBottomController.setBoard(clonedBoard);
                gridBottomController.checkExistentMosaikTiles();

                options.setBoard(clonedBoard);
            }
        } else {
            gui.showSkipSolvabilityCheck();
        }
    }
}