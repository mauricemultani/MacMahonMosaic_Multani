package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.MosaicTile;
import logic.Rotation;

import java.util.Objects;

/**
 * Steuerung des GridPanes in "<BorderPane> <bottom>".
 *
 * @author Maurice Singh Multani
 */
public class GridBottomController {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    private static GridPane gridPane;

    /**
     *  Konstruktor zur Zuweisung des GridPanes.
     * @param gridPane Das GridPane, was vom Controller verwaltet wird.
     */
    public GridBottomController(GridPane gridPane) {
        GridBottomController.gridPane = gridPane;
    }

    /**
     * String, dass die Mosaikteile speichert.
     */
    private String[] images() {
        return new String[]{
                "/gui/tiles/RGGG.png",
                "/gui/tiles/RGYG.png",
                "/gui/tiles/RGYR.png",
                "/gui/tiles/RRRR.png",
                "/gui/tiles/RRYY.png",
                "/gui/tiles/RYGR.png",
                "/gui/tiles/RYGY.png",
                "/gui/tiles/RYYY.png",

                "/gui/tiles/GGGG.png",
                "/gui/tiles/GGRR.png",
                "/gui/tiles/GRGR.png",
                "/gui/tiles/GRRR.png",
                "/gui/tiles/GRYG.png",
                "/gui/tiles/GRYR.png",
                "/gui/tiles/GYRG.png",
                "/gui/tiles/GYYY.png",

                "/gui/tiles/YGGG.png",
                "/gui/tiles/YGRY.png",
                "/gui/tiles/YGYG.png",
                "/gui/tiles/YRGY.png",
                "/gui/tiles/YRRR.png",
                "/gui/tiles/YRYR.png",
                "/gui/tiles/YYGG.png",
                "/gui/tiles/YYYY.png",
        };
    }

    /**
     * Initialisierung der Bilder auf das GridPane.
     * Hier wird auch das ziehen bzw. draggen der Bilder implementiert
     */
    public void initImages() {
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
                tile.setUserData(Rotation.DEGREE_0);

                // Drag starten
                TileActions.gridBottomActions(gridPane, tile, imageView, tileValue);

                gridPane.add(tile, column, row);
                imageIndex++;
            }
        }
    }

    private void fitFieldImageView(ImageView imageView){
        final int columnCount = gridPane.getColumnCount();

        imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
        imageView.fitHeightProperty().bind(gridPane.heightProperty());
        imageView.setPreserveRatio(true);
    }

}