package gui.controller;

import gui.TileActions;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.Board;
import logic.BoardCell;
import logic.MosaicTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Steuerung des GridPanes in "<BorderPane> <bottom>".
 *
 * @author Maurice Singh Multani
 */
public class GridBottomController {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    private final GridPane gridPane;

    private Board board;

    /**
     *  Konstruktor zur Zuweisung des GridPanes.
     * @param gridPane Das GridPane, was vom Controller verwaltet wird.
     */
    public GridBottomController(GridPane gridPane, Board board) {
        this.gridPane = gridPane;
        this.board = board;
    }

    /**
     * Ein Set aus den platzierbaren Mosaikteilen in der Class Mosaictile.
     */
    private static final Set<MosaicTile> USABLE_TILES = Set.of(
            MosaicTile.GGGG, MosaicTile.GGRR, MosaicTile.GRGR, MosaicTile.GRRR,
            MosaicTile.GRYG, MosaicTile.GRYR, MosaicTile.GYRG, MosaicTile.GYYY,

            MosaicTile.RGGG, MosaicTile.RGYG, MosaicTile.RGYR, MosaicTile.RRRR,
            MosaicTile.RRYY, MosaicTile.RYGR, MosaicTile.RYGY, MosaicTile.RYYY,

            MosaicTile.YGGG, MosaicTile.YGRY, MosaicTile.YGYG, MosaicTile.YRGY,
            MosaicTile.YRRR, MosaicTile.YRYR, MosaicTile.YYGG, MosaicTile.YYYY

    );

    /**
     * Initialisierung der Bilder auf das GridPane.
     * Hier wird auch das ziehen bzw. draggen der Bilder implementiert
     */
    public void initImages() {
        gridPane.getChildren().clear();

        final int columnCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        int imageIndex = 0;

        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnCount; column++) {

                MosaicTile tileValue = MosaicTile.values()[imageIndex];
                String path = tileValue.getImagePath();

                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));

                ImageView imageView = new ImageView(img);

                fitFieldImageView(imageView);

                Label tile = new Label();
                tile.setGraphic(imageView);

                // Drag starten
                TileActions.gridBottomActions(gridPane, tile, imageView, tileValue);

                gridPane.add(tile, column, row);
                imageIndex++;
            }
        }

        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);
    }

    /**
     * Passt die Bilder an das GridPane <GridBottom> an.
     *
     * @param imageView das ImageView
     */
    private void fitFieldImageView(ImageView imageView){
        final int columnCount = gridPane.getColumnCount();

        imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
        imageView.fitHeightProperty().bind(gridPane.heightProperty());
        imageView.setPreserveRatio(true);
    }

    /**
     * Vergleicht gespeicherte Mosaikteile mit den anderen Mosaikteilen, damit diese übersprungen werden.
     * Soll mehrfache Verwendung gleicher Mosaikteile verhindern
     */
    public void checkExistentMosaikTiles() {
        final int columnCount = gridPane.getColumnCount();

        gridPane.getChildren().clear();

        List<MosaicTile> usableTiles = new ArrayList<>(USABLE_TILES);

        int rows = board.getRows();
        int cols = board.getColumns();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                BoardCell cell = board.getCell(row, col);
                if (cell.isPlaced() && !cell.isHole() && cell.getTile() != null) {
                    usableTiles.remove(cell.getTile());
                }
            }
        }

        int imageIndex = 0;
        for (MosaicTile tileValue : usableTiles) {
            int row = imageIndex / columnCount;
            int col = imageIndex % columnCount;


            String path = tileValue.getImagePath();
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            ImageView imageView = new ImageView(img);

            fitFieldImageView(imageView);

            Label tile = new Label();
            tile.setGraphic(imageView);

            TileActions.gridBottomActions(gridPane, tile, imageView, tileValue);

            gridPane.add(tile, col, row);
            imageIndex++;
        }

        gridPane.setGridLinesVisible(false);
        gridPane.setGridLinesVisible(true);
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}