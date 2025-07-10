package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import logic.*;

import java.util.Objects;

import static gui.TileActions.getDegrees;

/**
 * Steuerung des Spielfeldes.
 *
 * @author Maurice Singh Multani
 */
public class BoardController {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Das Spielfeld.
     */
    private Board board;

    /**
     * Pane, die das Spielfeld enthält (Wichtig für die Größenverhältnisse).
     */
    @FXML
    private Pane gameFieldPane;

    /**
     * Konstruktor, welches ein GameFieldController mit einem GridPane initialisiert.
     * @param gridPane das GridPane, was mit dem GameFieldController initialisiert wird.
     * @param board             das Spielfeld
     * @param gameFieldPane     die Pane, die das Spielfeld enthält.
     */
    public BoardController(GridPane gridPane, Board board, Pane gameFieldPane){
        this.gridPane = gridPane;
        this.board = board;
        this.gameFieldPane = gameFieldPane;
    }

    /**
     * Hier wird das Board initialisiert.
     * Die Methode wird als einzige Methode an der MacMahonUIController Class
     * übergeben.
     */
    public void initializeBoard() {
        // Überschreiben von Zeilen und Spalten des GridPanes.
        createBoard();

        // Anpassung Zeilen und Spalten des Spielfelds
        adjustRowsAndColumns();
        adjustGameField(board.getColumns(), board.getRows());

        // Anpassung des Spielfeldes bei Größenveränderung
        // Spielfeld bleibt quadratisch
        gameFieldPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustGameField(newVal.doubleValue(), gameFieldPane.getHeight());
        });
        gameFieldPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            adjustGameField(gameFieldPane.getWidth(), newVal.doubleValue());
        });

        // Initialisiert die Bilder an den Grenzen des Spielfeldes
        initImagesBorderBoard();

        // Ermöglicht das droppen der Mosaikteile
        dropTiles();

        // Erstellt Löcher auf dem Spielfeld, wenn es mehr als 24 belegbare Zellen gibt.
        generatingHoles();

        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);
    }

    /**
     * Methode, welche das Board aktualisieren soll,
     * wenn eine gespeicherte Datei geladen werden soll.
     */
    public void updateBoardWhenLoading() {
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();

        // Überschreiben von Zeilen und Spalten des GridPanes.
        createBoard();

        // Anpassung Zeilen und Spalten des Spielfelds
        adjustRowsAndColumns();
        adjustGameField(board.getColumns(), board.getRows());

        // Anpassung des Spielfeldes bei Größenveränderung
        // Spielfeld bleibt quadratisch
        gameFieldPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustGameField(newVal.doubleValue(), gameFieldPane.getHeight());
        });
        gameFieldPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            adjustGameField(gameFieldPane.getWidth(), newVal.doubleValue());
        });

        // setzt eine Mindestgröße beim Spielfeld
        gridPane.setMinSize(450, 450);

        // Initialisiert die Bilder an den Grenzen des Spielfeldes
        initImagesBorderBoard();

        // stellt die gespeicherten Mosaikteile (Ränder mit inbegriffen) dar
        displaySavedTiles();

        // Ermöglicht das droppen der Mosaikteile
        dropTiles();

        // Erstellt die Löcher auf dem Spielfeld.
        // geschieht nur, wenn es mehr als 24 belegbare Zellen gibt.
        generatingHoles();

        // setzt die GridLines auf sichtbar
        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);
    }

    /**
     * Erstellt ein Spielfeld.
     * Das Board darf nicht mit weniger als 4, oder mehr als 8 Zeilen oder Spalten generiert werden.
     * Bedingungen sind mit entsprechenden Exceptions gesetzt.
     */
    private void createBoard() {
        // Exception, falls das Board 0 Reihen oder 0 Spalten hat.
        if (board.getRows() == 0 || board.getColumns() == 0) {
            throw new IllegalArgumentException("Column or Row is Empty");
        }

        // Exception, falls das Board weniger als 4 Zeilen oder Spalten hat
        if (board.getRows() < 4 || board.getColumns() < 4) {
            throw new IllegalArgumentException("Das Feld muss mindestens 4 Zeilen und Spalten haben");
        }

        // Exception, falls das Board mehr als 8 Zeilen und Spalten hat
        if (board.getRows() > 8 || board.getColumns() > 8) {
            throw new IllegalArgumentException("Das Feld darf maximal 8 Zeilen und Spalten haben");
        }

        // löscht die vorhanden Reihen und Spalten Constraints
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();

        // überschreibt neue Constraints mit der Anzahl an Reihen
        for (int row = 0; row < board.getRows(); row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(2);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // überschreibt neue Constraints mit der Anzahl an Spalten
        for (int column = 0; column < board.getColumns(); column++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setMinWidth(2);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    /**
     *  Mit dieser Methode soll, bei einem Spielstand was geladen wird, das Spiel erneuert werden.
     */
    public void setBoardAndUpdate(Board newBoard) {
        this.board = newBoard;
        updateBoardWhenLoading();
    }

    /**
     * Die Methode lädt eine gespeicherte Datei (jeweils belegte Zellen, als auch die Ränder)
     * auf dem Spielfeld.
     */
    private void displaySavedTiles() {
        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                BoardCell cell = board.getCell(row, col);

                if (cell.isPlaced() && !cell.isHole()) {
                    MosaicTile tile = cell.getTile();
                    Rotation rotation = cell.getRotation();

                    Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tile.getImagePath())));
                    ImageView imageView = new ImageView(img);

                    imageView.setRotate(getDegrees(rotation));
                    fitFieldImageView(imageView, rotation);

                    Label label = new Label();
                    label.setGraphic(imageView);
                    label.setUserData(rotation);

                    gridPane.add(label, col, row);

                    Position pos = new Position(row, col);
                    TileActions.boardActions(gridPane, board, label, imageView, pos, tile);
                }
            }
        }
    }

    /**
     * Anpassen der Größe des Spielfeldes mit der Anpassung,
     * dass die Randzellen um 1/4 kleiner als die Spielfeldzellen sind.
     */
    private void adjustRowsAndColumns() {
        double middleRowHeightPercentage = 100 / (board.getRows() - 1d);
        double borderRowHeightPercentage = middleRowHeightPercentage / 4;

        double middleColWidthPercentage = 100 / (board.getColumns() - 1d);
        double borderColWidthPercentage = middleColWidthPercentage / 4;

        gridPane.getRowConstraints().getFirst().setPercentHeight(borderRowHeightPercentage);
        gridPane.getRowConstraints().get(board.getRows() - 1).setPercentHeight(borderRowHeightPercentage);

        gridPane.getColumnConstraints().getFirst().setPercentWidth(borderColWidthPercentage);
        gridPane.getColumnConstraints().get(board.getColumns() - 1).setPercentWidth(borderColWidthPercentage);

        for (int x = 1; x < board.getColumns() - 1; x++) {
            gridPane.getColumnConstraints().get(x).setPercentWidth(middleColWidthPercentage);
        }
        for (int y = 1; y < board.getRows() - 1; y++) {
            gridPane.getRowConstraints().get(y).setPercentHeight(middleRowHeightPercentage);
        }
    }

    /**
     * Anpassen der mittleren Zellen des Spielfeldes.
     * Sollen quadratisch bleiben
     *
     * @param width  Die Breite der Pane
     * @param height Die Höhe der Pane
     */
    void adjustGameField(double width, double height) {
        final int middleRows = board.getRows() - 2;
        final int middleColumns = board.getColumns() - 2;

        final double outerColumn = middleColumns + 0.5d;
        final double outerRows = middleRows + 0.5d;

        if (width > 0 && height > 0 && middleRows > 0 && middleColumns > 0) {
            double gridCellSize = Math.min(width / outerColumn, height / outerRows);
            gridPane.setPrefSize(gridCellSize * outerColumn, gridCellSize * outerRows);
        }
    }

    /**
     * Methode zum droppen der Mosaikteile. Speziell für Board
     * wegen Berechnungen der Zellen.
     */
    private void dropTiles() {
        gridPane.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);

            }
            dragEvent.consume();
        });

        gridPane.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            if (db.hasString()) {

                double totalWidth = gridPane.getWidth();
                double totalHeight = gridPane.getHeight();

                int middleCols = gridPane.getColumnCount() - 2;
                int middleRows = gridPane.getRowCount() - 2;

                double cellWidth = totalWidth / (middleCols + 0.5);
                double cellHeight = totalHeight / (middleRows + 0.5);

                double x = dragEvent.getX();
                double y = dragEvent.getY();

                double borderWidth = cellWidth * 0.25;
                double borderHeight = cellHeight * 0.25;

                int column = (int) ((x - borderWidth) / cellWidth);
                int row = (int) ((y - borderHeight) / cellHeight);

                if (!TileActions.isCellEmpty(gridPane, column + 1, row + 1)) {
                    dragEvent.setDropCompleted(false);
                    dragEvent.consume();
                    return;
                }

                if (column >= 0 && column < middleCols && row >= 0 && row < middleRows) {
                    // String[] tileInfo = db.getString().split("/");
                    // MosaicTile tile = MosaicTile.valueOf(tileInfo[0]);
                    // Rotation rotation = Rotation.valueOf(tileInfo[1]);

                    String tileInfo = db.getString();
                    MosaicTile tile = MosaicTile.valueOf(tileInfo);

                    ImageView imageView = new ImageView(db.getImage());
                    // imageView.setRotate(getDegrees(rotation));

                    Label droppedLabel = new Label();
                    droppedLabel.setGraphic(imageView);
                    // droppedLabel.setUserData(rotation);

                    Position position = new Position(row + 1, column + 1);
                    board.placeTileAt(tile, Rotation.DEGREE_0, position);

                    gridPane.add(droppedLabel, column + 1, row + 1);
                    TileActions.boardActions(gridPane, board, droppedLabel, imageView, position, tile);
                }
            }
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });
    }

    /**
     * Private Methode die Löcher generiert,
     * wenn das Spielfeld mehr als 24 belegbare Zellen hat.
     */
    private void generatingHoles() {
        Rotation rotation = Rotation.DEGREE_0;
        boolean[][] holes = board.getHoles();

        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int column = 1; column < board.getColumns() - 1; column++) {
                if (holes[row][column]) {
                    Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
                    ImageView imageView = new ImageView(img);

                    fitFieldImageView(imageView, rotation);

                    Label hole = new Label();
                    hole.setGraphic(imageView);

                    gridPane.add(hole, column, row);
                }

            }
        }
    }

    /**
     * Initialisierung der Bilder an den Grenzen des Spielfeldes.
     */
    private void initImagesBorderBoard() {
        setImagesColumnBorder();
        setImagesRowBorder();
    }

    /**
     * Initialisierung der Bilder an der linken und rechten Grenze vom Spielfeld.
     * Wird in initImagesBorderGameField verwendet.
     *
     */
    private void setImagesColumnBorder() {
        String[] leftBorderColors = board.getLeftBorderColors();
        String[] rightBorderColors = board.getRightBorderColors();

            for (int row = 1; row < board.getRows() - 1; row++){
                ImageView leftBorderImages = new ImageView(leftBorderColors[row]);
                gridPane.add(leftBorderImages, 0, row);
                fitBorderImageView(leftBorderImages, true);

                ImageView rightBorderImages = new ImageView(rightBorderColors[row]);
                gridPane.add(rightBorderImages, board.getColumns() - 1, row);
                fitBorderImageView(rightBorderImages, true);
            }
    }

    /**
     * Initialisierung der Bilder an der oberen und unteren Grenze vom Spielfeld.
     * Wird in initImagesBorderGameField verwendet.
     */
    private void setImagesRowBorder() {
        String[] topBorderColors = board.getTopBorderColors();
        String[] bottomBorderColors = board.getBottomBorderColors();

            for (int columns = 1; columns < board.getColumns() - 1; columns++){
                ImageView topBorderImages = new ImageView(topBorderColors[columns]);
                gridPane.add(topBorderImages, columns, 0);
                fitBorderImageView(topBorderImages, false);

                ImageView bottomBorderImages = new ImageView(bottomBorderColors[columns]);
                gridPane.add(bottomBorderImages, columns, board.getRows() - 1);
                fitBorderImageView(bottomBorderImages, false);
            }
    }

    /**
     * Anpassen der Mosaikteile an dem Spielfeld.
     * Die Mosaikteile sollen sich mit der Vergrößerung/Verkleinerung des Spielfeldes
     * sich anpassen.
     * @param imageView das Bild vom Mosaikteil
     */
    private void fitFieldImageView(ImageView imageView, Rotation rotation) {
        int columnCount = gridPane.getColumnCount();
        int rowsCount = gridPane.getRowCount();

        // Anpassen der Bilder anhand der Rotation
        // Methode verwendet
        if (getDegrees(rotation) == 0 || getDegrees(rotation) == 180) {
            imageView.fitWidthProperty().bind(
                    gridPane.widthProperty().divide(columnCount - 1).
                            subtract(gridPane.getHgap())
            );
            imageView.fitHeightProperty().bind(
                    gridPane.heightProperty().divide(rowsCount - 1).
                            subtract(gridPane.getVgap())
            );
        } else if (getDegrees(rotation) == 90 || getDegrees(rotation) == 270){
            imageView.fitWidthProperty().bind(
                    gridPane.heightProperty().divide(rowsCount - 1).
                            subtract(gridPane.getVgap())
            );
            imageView.fitHeightProperty().bind(
                    gridPane.widthProperty().divide(columnCount - 1).
                            subtract(gridPane.getHgap())
            );
        }
    }

    /**
     * Hilfsmethode um die Bilder automatisch an der Breite und Höhe des GridPanes anzupassen.
     * Die Hilfsmethode soll redundanten Code in den Methoden reduzieren.
     * <p>
     * Die if-Bedingung gilt für die Grenzen links und rechts vom Spielfeld.
     * die else-Bedingung gilt für die Grenzen oben und unten vom Spielfeld.
     *
     * @param imageView      das ImageView, dessen Größe angepasst werden soll
     * @param isColumnBorder Boolescher Wert um die Bedingungen klarzustellen.
     *                       Wenn true, dann handelt es sich um die Grenzen links und rechts vom Spielfeld.
     *                       Wenn false, dann handelt es sich um die Grenzen oben und unten vom Spielfeld.
     */
    private void fitBorderImageView(ImageView imageView, boolean isColumnBorder) {
        double middleRowHeightPercentage = 100 / (board.getRows() - 1d);
        double borderRowHeightPercentage = middleRowHeightPercentage / 4;

        double middleColWidthPercentage = 100 / (board.getColumns() - 1d);
        double borderColWidthPercentage = middleColWidthPercentage / 4;

        if (isColumnBorder) {
            imageView.fitWidthProperty().bind(gridPane.widthProperty().
                    multiply(borderColWidthPercentage / 100));
            imageView.fitHeightProperty().bind(gridPane.heightProperty().
                    multiply(middleRowHeightPercentage / 100));
        } else {
            imageView.fitWidthProperty().bind(gridPane.widthProperty().
                    multiply(middleColWidthPercentage / 100));
            imageView.fitHeightProperty().bind(gridPane.heightProperty().
                    multiply(borderRowHeightPercentage / 100));
        }
    }
}
