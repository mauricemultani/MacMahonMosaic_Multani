package gui.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public interface GridBottomController {

    String[] imagePaths = {
            "tiles/YGGG.png",
            "tiles/YGRY.png",
            "tiles/YGYG.png",
            "tiles/YRGY.png",
            "tiles/YRRR.png",
            "tiles/YRYR.png",
            "tiles/YYGG.png",
            "tiles/YYYY.png",
    };

    default void initImagesBottom(GridPane gridPane) {
        final int columnCount = gridPane.getColumnCount();

        ImageView[] imageViews = new ImageView[columnCount];

        for (int i = 0; i < imagePaths.length; i++) {
            imageViews[i] = new ImageView();

            Image img = new Image(getClass().getResourceAsStream(imagePaths[i]));
            imageViews[i].setImage(img);

            gridPane.add(imageViews[i], i, 0);

            imageViews[i].fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
            imageViews[i].fitHeightProperty().bind(gridPane.heightProperty());
        }

    }

    /**
     * Anpassen der Zellen
     * Sollen quadratisch bleiben
     * @param gridPane  Das Spielfeld, dessen Größe angepasst werden soll
     * @param width     Die Breite der Pane
     * @param height    Die Höhe der Pane
     */
    default void adjustBottomGrid(GridPane gridPane, double width, double height) {

        final int middleRows = gridPane.getRowCount();
        final int middleColumns = gridPane.getColumnCount();

        if (width > 0 && height > 0 && middleRows > 0 && middleColumns > 0) {
            double gridCellSize = Math.min(width / middleColumns, height / middleRows);
            gridPane.setPrefSize(gridCellSize * middleColumns, gridCellSize * middleRows);
        }
    }
}
