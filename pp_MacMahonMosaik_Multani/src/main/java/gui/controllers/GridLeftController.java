package gui.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public interface GridLeftController {

    String[] imagePaths = {
            "tiles/GGGG.png",
            "tiles/GGRR.png",
            "tiles/GRGR.png",
            "tiles/GRRR.png",
            "tiles/GRYG.png",
            "tiles/GRYR.png",
            "tiles/GYRG.png",
            "tiles/GYYY.png",
    };

    default void initImagesLeft(GridPane gridPane) {
        final int rowsCount = gridPane.getRowCount();

        ImageView[] imageViews = new ImageView[rowsCount];

        for (int i = 0; i < imagePaths.length; i++) {
            imageViews[i] = new ImageView();

            Image img = new Image(getClass().getResourceAsStream(imagePaths[i]));
            imageViews[i].setImage(img);

            gridPane.add(imageViews[i], 0, i);

            imageViews[i].fitWidthProperty().bind(gridPane.widthProperty());
            imageViews[i].fitHeightProperty().bind(gridPane.heightProperty().divide(rowsCount));
        }

    }
}
