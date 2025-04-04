package gui.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public interface GameFieldController {


    /**
     * String, dass Bilder speichert. Sind für die Grenzen des GameFields gemacht.
     * Die leeren Strings sollen die jeweiligen Ecken ([0][0], [3][0], [0][2], [3][2]) in dem GameField sein.
     */
    String[] imagePaths = {
            "",
            "tiles/YYYY_ohne_Rand.png",
            "tiles/RRRR_ohne_Rand.png",
            "",
            "tiles/RRRR.png",
            "tiles/RYGR.png",
            "tiles/RYGY.png",
            "tiles/YYYY.png",
            "",
            "tiles/RRRR.png",
            "tiles/YYYY.png",
            ""
    };


    /**
     * Anpassen der Größe des Spielfeldes mit der Anpassung, dass die Randzellen um 1/4 kleiner als die Spielfeldzellen sind.
     * @param gridPane
     */
    default void adjustRowsAndColumnsGameField(GridPane gridPane){
        int cols = gridPane.getColumnCount();
        int rows = gridPane.getRowCount();
        double middleRowHeightPercentage = 100 / (rows - 1d);
        double borderRowHeightPercentage = middleRowHeightPercentage / 4;

        double middleColWidthPercentage = 100 / (cols - 1d);
        double borderColWidthPercentage = middleColWidthPercentage / 4;

        gridPane.getRowConstraints().getFirst().setPercentHeight(borderRowHeightPercentage);
        gridPane.getRowConstraints().getLast().setPercentHeight(borderRowHeightPercentage);

        gridPane.getColumnConstraints().getFirst().setPercentWidth(borderColWidthPercentage);
        gridPane.getColumnConstraints().getLast().setPercentWidth(borderColWidthPercentage);

        for (int x = 1; x < cols - 1; x++) {
            gridPane.getColumnConstraints().get(x).setPercentWidth(middleColWidthPercentage);
        }
        for (int y = 1; y < rows - 1; y++) {
            gridPane.getRowConstraints().get(y).setPercentHeight(middleRowHeightPercentage);
        }
    }

    /**
     * Anpassen der mittleren Zellen des Spielfeldes
     * Sollen quadratisch bleiben
     * @param gridPane  Das Spielfeld, dessen Größe angepasst werden soll
     * @param width     Die Breite der Pane
     * @param height    Die Höhe der Pane
     */
    default void adjustGameField(GridPane gridPane, double width, double height) {

        final int middleRows = gridPane.getRowCount() - 2;
        final int middleColumns = gridPane.getColumnCount() - 2;

        final double outerColumn = middleColumns + 0.5d;
        final double outerRows = middleRows + 0.5d;

        if (width > 0 && height > 0 && middleRows > 0 && middleColumns > 0) {
            double gridCellSize = Math.min(width / outerColumn, height / outerRows);
            gridPane.setPrefSize(gridCellSize * outerColumn, gridCellSize * outerRows);
        }
    }

    /**
     * Initialisierung der Bilder an das GridPane
     * @param gridPane das GridPane, auf das die Bilder angezeigt werden sollen.
     * @return imageViews das zweidimensionale Array mit der Anpassung der Größe
     */
    default ImageView[][] initImagesGameField(GridPane gridPane){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        ImageView[][] imageViews = new ImageView[rowsCount - 2][columnsCount - 2];

        for (int row = 0; row < rowsCount - 2; row++){
            for (int column = 0; column < columnsCount - 2; column++){

                imageViews[row][column] = new ImageView();

                imageViews[row][column].setImage(new Image(getClass().getResourceAsStream("tiles/GGGG.png")));
                gridPane.add(imageViews[row][column], column + 1, row + 1);


                imageViews[row][column].fitWidthProperty().bind(gridPane.widthProperty().
                        divide(columnsCount - 1).subtract(gridPane.getHgap()));
                imageViews[row][column].fitHeightProperty().bind(gridPane.heightProperty().
                        divide(rowsCount - 1).subtract(gridPane.getVgap()));
            }
        }
        return imageViews;
    }

    /**
     * Intitialisierung der Bilder an den Grenzen des Spielfeldes
     * @param gridPane das Feld, an dem die Bilder an den Rändern angepasst werden soll.
     * @return imageViews, die Bilder im korrekten Schnitt
     */
    default ImageView[][] initImagesOnBorderGameField(GridPane gridPane){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        ImageView[][] imageViews = new ImageView[rowsCount][columnsCount];

        for (int row = 0; row < rowsCount; row++){
            for (int column = 0; column < columnsCount; column++){

                imageViews[0][column] = new ImageView();

                Image image = new Image(getClass().getResourceAsStream(imagePaths[column]));
                ImageView imageView = new ImageView(image);


                imageViews[0][column] = imageView;
                gridPane.add(imageView, column, 0);

                imageView.fitWidthProperty().bind(gridPane.widthProperty().
                        divide(columnsCount - 1).subtract(gridPane.getHgap()));
                imageView.fitHeightProperty().bind(gridPane.heightProperty().
                        divide(rowsCount * rowsCount - 1).subtract(gridPane.getVgap()));

                imageViews[2][column] = new ImageView();

                Image image2 = new Image(getClass().getResourceAsStream(imagePaths[column]));
                ImageView imageView1 = new ImageView(image2);


                imageViews[2][column] = imageView1;
                gridPane.add(imageView1, column, 2);

                imageView1.fitWidthProperty().bind(gridPane.widthProperty().
                        divide(columnsCount - 1).subtract(gridPane.getHgap()));
                imageView1.fitHeightProperty().bind(gridPane.heightProperty().
                        divide(rowsCount * rowsCount - 1).subtract(gridPane.getVgap()));

                imageViews[1][0] = new ImageView();

                Image image3 = new Image(getClass().getResourceAsStream("tiles/GGGG_ohne_Rand.png"));
                ImageView imageView2 = new ImageView(image3);

                imageViews[1][0] = imageView2;
                gridPane.add(imageView2, 0, 1);

                imageView2.fitWidthProperty().bind(gridPane.widthProperty().
                        divide(columnsCount * columnsCount - 4).subtract(gridPane.getHgap()));
                imageView2.fitHeightProperty().bind(gridPane.heightProperty().
                        divide(rowsCount - 1).subtract(gridPane.getHgap()));

                imageViews[1][3] = new ImageView();

                Image image4 = new Image(getClass().getResourceAsStream("tiles/YYYY_ohne_Rand.png"));
                ImageView imageView3 = new ImageView(image4);

                imageViews[1][3] = imageView3;
                gridPane.add(imageView3, 3, 1);

                imageView3.fitWidthProperty().bind(gridPane.widthProperty().
                        divide(columnsCount * columnsCount - 4).subtract(gridPane.getHgap()));
                imageView3.fitHeightProperty().bind(gridPane.heightProperty().
                        divide(rowsCount - 1).subtract(gridPane.getHgap()));
            }
        }

    return imageViews;
    }
}
