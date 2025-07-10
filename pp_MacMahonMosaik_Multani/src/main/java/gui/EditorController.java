package gui;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;
import logic.*;

import java.util.Objects;
import java.util.Optional;

/**
 * Eine Editor-Kontroller Class.
 * Sie übernimmt die Verantwortlichkeiten von dem Editor.
 * Hauptgrund für die EditorController Klasse war es
 * um in BoardController Platz zu sparen.
 *
 * @author Maurice Singh Multani
 */
public class EditorController {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    private final GridPane gridPane;

    /**
     * Das Spielfeld.
     */
    private Board board;

    /**
     * Pane, die das Spielfeld enthält (Wichtig für die Größenverhältnisse).
     */
    private final Pane gameFieldPane;

    /**
     *
     */
    private final BoardController boardController;

    private final JavaFXGUI gui;

    private final Game game;

    private final GridBottomController gridBottomController;

    private boolean gridBottomInitialized = false;

    /**
     * Konstruktor, welches ein GameFieldController mit einem GridPane initialisiert.
     * @param gridPane          das GridPane, was mit dem GameFieldController initialisiert wird.
     * @param board             das Spielfeld
     * @param gameFieldPane     die Pane, die das Spielfeld enthält.
     */
    public EditorController(GridPane gridPane, Board board, Pane gameFieldPane, BoardController boardController, JavaFXGUI gui, Game game, GridBottomController gridBottomController){
        this.gridPane = gridPane;
        this.board = board;
        this.gameFieldPane = gameFieldPane;
        this.boardController = boardController;
        this.gui = gui;
        this.game = game;
        this.gridBottomController = gridBottomController;
    }

    /**
     * Die Methode soll die Möglichkeiten bei Aktivierung des Editormodus
     * in einer Methode zusammenfassen.
     */
    public void initializeEditorMode() {
        gridPane.getChildren().clear();

        switchToEditorMode();

        // Anpassung des Spielfeldes bei Größenveränderung
        // Spielfeld bleibt quadratisch
        gameFieldPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            boardController.adjustGameField(newVal.doubleValue(), gameFieldPane.getHeight());
        });
        gameFieldPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            boardController.adjustGameField(gameFieldPane.getWidth(), newVal.doubleValue());
        });

        // setzt eine Mindestgröße beim Spielfeld
        gridPane.setMinSize(450, 450);

        if (!gridBottomInitialized) {
            gridBottomController.initImages();
            gridBottomInitialized = true;
        }

        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                Label label = new Label();
                gridPane.add(label, col, row);
            }
        }
    }

    /**
     * Die Methode soll bei einem Switch in den Editor-Modus alles
     * außer den Rändern und Löchern leeren.
     */
    public void switchToEditorMode() {
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < columns - 1; col++) {
                BoardCell cell = board.getCell(row, col);
                if (cell.isPlaced() && !cell.isHole()) {
                    cell.placeTile(MosaicTile.NNNN, Rotation.DEGREE_0);
                }
            }
        }
        boardController.initializeBoard();
    }

    /**
     * Die Methode soll den Spieler die Möglichkeit geben die Farben der Ränder anzupassen.
     */
    public void adjustBorderColors() {

    }

    /**
     * Methode zur Anpassung der Größe des Spielfeldes.
     * Spielfeld muss mind. 2 und max. 6 Zellen hoch und breit sein.
     * Muss rechteckig, aber nicht quadratisch sein.
     */
    public void changeSizeOfGameField() {
        Optional<Pair<Integer, Integer>> result = gui.whenChangeSizeOfGameField();

        result.ifPresent(pair -> {
            int rows = pair.getKey();
            int columns = pair.getValue();

            // Exception, falls das Board weniger als 4 Zeilen oder Spalten hat
            if (rows < 4 || columns < 4) {
                gui.showFieldTooSmall();
                return;
            }

            // Exception, falls das Board mehr als 8 Zeilen und Spalten hat
            if (rows > 8 || columns > 8) {
                gui.showFieldTooBig();
                return;
            }

            // löscht die vorhanden Reihen und Spalten Constraints
            gridPane.getChildren().clear();
            gridPane.getRowConstraints().clear();
            gridPane.getColumnConstraints().clear();

            // überschreibt neue Constraints mit der Anzahl an Reihen
            for (int row = 0; row < rows; row++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(2);
                gridPane.getRowConstraints().add(rowConstraints);
            }

            // überschreibt neue Constraints mit der Anzahl an Spalten
            for (int col = 0; col < columns; col++) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setMinWidth(2);
                gridPane.getColumnConstraints().add(columnConstraints);
            }

            Board newBoard = new Board(rows, columns, false);
            this.board = newBoard;
            game.setBoard(newBoard);

            boardController.setBoardAndUpdate(newBoard);

            int gameFieldRows = board.getRows() - 2;
            int gameFieldCols = board.getColumns() - 2;
            int totalCells = gameFieldRows * gameFieldCols;

            int maxTiles = 24;
            int numHoles = Math.max(0, totalCells - maxTiles);

            if (numHoles > 0) {
                choosePositionsOfHoles();
            }
        });
    }

    /**
     * Hilfsmethode für changeSizeOfGameField().
     *
     * Spieler bestimmt Positionen der Löcher, wenn dass Spielfeld nach seiner Erstellung
     * mehr als 24 Zellen hat.
     */
    private void choosePositionsOfHoles(){
        int gameFieldRows = board.getRows() - 2;
        int gameFieldCols = board.getColumns() - 2;
        int totalCells = gameFieldRows * gameFieldCols;

        int maxTiles = 24;
        int numHoles = Math.max(0, totalCells - maxTiles);

        if (numHoles == 1) {
            gui.showOnlyOneHoleToPlace();
            placingHoles(numHoles);

        } else if (numHoles > 1) {
            gui.showHolesToBePlaced(numHoles);
            placingHoles(numHoles);
        }
    }

    /**
     * Die Methode lässt den Spieler letztlich die Löcher platzieren.
     * @param numHoles      Die Anzahl an zu setzenden Löchern
     */
    private void placingHoles(int numHoles) {
        int[] placedHoles = {0};
        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                Node node = getNode(gridPane, col, row);
                if (node != null) {
                    int finalRow = row;
                    int finalCol = col;

                    node.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY && placedHoles[0] < numHoles) {
                            board.getCell(finalRow, finalCol).setHole();

                            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
                            ImageView imageView = new ImageView(image);

                            if (node instanceof Label label) {
                                label.setGraphic(imageView);
                            }

                            placedHoles[0]++;

                            if (placedHoles[0] == numHoles) {
                                removeMouseEvent();
                                gui.showAllHolesPlaced();
                                boardController.setBoardAndUpdate(board);
                            }
                        }
                    });
                    node.setCursor(Cursor.HAND);
                }
            }
        }
    }

    /**
     * Entfernt das MouseEvent.
     * Reduziert redundanten Code.
     */
    private void removeMouseEvent() {
        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                Node node = getNode(gridPane, col, row);
                if (node instanceof Label label) {
                    label.setOnMouseClicked(null);
                }
            }
        }
    }

    /**
     * Geht die Zellen durch und schaut sich an
     * @param gridPane
     * @param column
     * @param row
     * @return
     */
    private Node getNode(GridPane gridPane, int column, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer col = GridPane.getColumnIndex(node);
            Integer rows = GridPane.getRowIndex(node);

            if (col != null && rows != null && col == column && rows == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * Methode welche prüft, ob ein Wechsel zurück in den Spielmodus möglich ist.
     *
     * Folgende Bedingungen müssen für einen Wechsel in den Spielmodus erreicht sein:
     * 1. Alle Ränder haben eine Farbe.
     * 2. Es müssen ausreichend Löcher vorhanden sein.
     * 3. Puzzle muss lösbar sein.
     */
    public boolean canSwitchBackToGameMode() {
        return false;
    }

    /**
     * Der Wechsel zurück in den Spielmodus.
     *
     * Findet bei folgenden Bedingungen statt:
     * 1. Alle Ränder haben eine Farbe.
     * 2. Es müssen ausreichend Löcher vorhanden sein. (zutreffend bei mehr als 24 Zellen)
     * 3. Puzzle muss lösbar sein.
     */
    public void switchBackToGameMode(){

    }

    /**
     * Speichert das laufende Spiel.
     */
    public void saveGame(){

    }

    /**
     * Lädt ein gespeichertes Spiel.
     * Wenn ein Puzzle mit Teilen auf dem Spielfeld geladen wird, sollen diese entfernt werden.
     */
    public void loadGame(){

    }
}
