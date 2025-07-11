package gui.controller;

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

import java.util.*;

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
     *  Zugriff auf die BoardController Class
     */
    private final BoardController boardController;

    /**
     * Zugriff auf die JavaFXGUI
     */
    private final GUIConnector gui;

    /**
     * Zugriff auf die Game Class
     */
    private final Game game;

    /**
     * Zugriff auf die GridBottomController Class.
     */
    private final GridBottomController gridBottomController;

    /**
     * private boolescher Wert der mit der Initialisierung
     * der Bilder in meinem gridBottom gridPane zusammenarbeitet.
     */
    private boolean gridBottomInitialized = false;

    /**
     * Ein Set aus den Mosaikteilen, die für die jeweiligen Ränder erlaubt sind.
     * Anhand der Namen erkennt man für welchen Rand die Sets sind.
     */
    private static final Set<MosaicTile> LEFT_BORDER_TILES = Set.of(
            MosaicTile.NYNN, MosaicTile.NRNN, MosaicTile.NGNN
    );

    private static final Set<MosaicTile> RIGHT_BORDER_TILES = Set.of(
            MosaicTile.NNNY, MosaicTile.NNNR, MosaicTile.NNNG
    );

    private static final Set<MosaicTile> TOP_BORDER_TILES = Set.of(
            MosaicTile.NNYN, MosaicTile.NNRN, MosaicTile.NNGN
    );

    private static final Set<MosaicTile> BOTTOM_BORDER_TILES = Set.of(
            MosaicTile.YNNN, MosaicTile.RNNN, MosaicTile.GNNN
    );


    /**
     * Konstruktor, welches ein GameFieldController mit einem GridPane initialisiert.
     * @param gridPane          das GridPane, was mit dem GameFieldController initialisiert wird.
     * @param board             das Spielfeld
     * @param gameFieldPane     die Pane, die das Spielfeld enthält.
     */
    public EditorController(GridPane gridPane, Board board, Pane gameFieldPane, BoardController boardController, GUIConnector gui, Game game, GridBottomController gridBottomController){
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

        adjustBorderColors();
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
        adjustColumnBorderColors();
        adjustRowBorderColors();
    }

    private void adjustColumnBorderColors() {
        for (int row = 1; row < board.getRows() - 1; row++) {
            Node leftNode = getNode(gridPane, 0, row);
            Node rightNode = getNode(gridPane, board.getColumns() - 1, row);

            List<MosaicTile> leftBorderTiles = new ArrayList<>(LEFT_BORDER_TILES);
            List<MosaicTile> rightBorderTiles = new ArrayList<>(RIGHT_BORDER_TILES);

            getNextImage(leftNode, leftBorderTiles, row, board.getLeftBorderColors());
            getNextImage(rightNode, rightBorderTiles, row, board.getRightBorderColors());
        }
    }

    private void adjustRowBorderColors() {
        for (int col = 1; col < board.getColumns(); col++) {
            Node topNode = getNode(gridPane, col, 0);
            Node bottomNode = getNode(gridPane, col, board.getRows() - 1);

            List<MosaicTile> topBorderTiles = new ArrayList<>(TOP_BORDER_TILES);
            List<MosaicTile> bottomBorderTiles = new ArrayList<>(BOTTOM_BORDER_TILES);

            getNextImage(topNode, topBorderTiles, col, board.getTopBorderColors());
            getNextImage(bottomNode, bottomBorderTiles, col, board.getBottomBorderColors());
        }
    }

    /**
     * Die Methode ist da um redundanten Code verringern.
     * Bei einem Klick auf eines der Randbilder wird das Bild gewechselt
     * und rüber zum nächsten Bild gegangen.
     * @param node          das Node
     * @param tileList      die ArrayList von den Randbildern
     * @param index         der Index vom
     * @param borderArray   die Farben vom jeweiligen Rand.
     */
    private void getNextImage(Node node, List<MosaicTile> tileList, int index, String[] borderArray) {
        if (node == null) {
            return;
        }

        node.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                // holt sich das Randbild mit dem jeweiligen Index
                String currentPath = borderArray[index];
                // konvertiert den Bildpfad zurück zu einem Enum
                MosaicTile currentTile = MosaicTile.convertImagePathToEnum(currentPath);

                int idx = tileList.indexOf(currentTile);
                int nextIdx = (idx + 1) % tileList.size();
                MosaicTile nextTile = tileList.get(nextIdx);

                borderArray[index] = nextTile.getImagePath();


                if (node instanceof ImageView imageView) {
                    Image image = new Image(nextTile.getImagePath());
                    imageView.setImage(image);
                }
            }
        });
        node.setCursor(Cursor.HAND);
    }

    /**
     * Methode zur Anpassung der Größe des Spielfeldes.
     * Spielfeld muss mind. 2 und max. 6 Zellen hoch und breit sein.
     * Muss rechteckig, aber nicht quadratisch sein.
     */
    public void changeSizeOfGameField() {
        Optional<Pair<Integer, Integer>> result = gui.whenChangeSizeOfGameField();

        if (result.isEmpty()) {
            gui.showMissingNumbersForField();
        }

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

        adjustBorderColors();
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
     * Diese Methode geht die Zellen durch und
     * gibt den Node einer bestimmten Position zurück.
     * @param gridPane  das GridPane auf der Schleife durchgegangen wird.
     * @param column    die Anzahl an Spalten
     * @param row       die Anzahl an Reihen
     * @return          Entweder das Node was gefunden wird, oder null.
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
