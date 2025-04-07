package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Steuerung des Spielfeldes.
 *
 * @author Maurice Singh Multani
 */
public class GameFieldController {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    private final GridPane gridPane;

    /**
     * Konstruktor, welches ein GameFieldController mit einem GridPane initialisiert.
     * @param gridPane das GridPane, was mit dem GameFieldController initialisiert wird.
     */
    public GameFieldController(GridPane gridPane){
        this.gridPane = gridPane;
    }

    /**
     * String, dass Bilder speichert. Sind für die Grenzen des GameFields gemacht.
     * Die leeren Strings sollen die jeweiligen Ecken ([0][0], [3][0], [0][2], [3][2]) in dem GameField sein.
     */
    String[] imagePaths = {
            "",
            "tiles/YYYY_ohne_Linien.png",
            "tiles/RRRR_ohne_Linien.png",
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
     */
    public void adjustRowsAndColumnsGameField(){
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
     * @param width     Die Breite der Pane
     * @param height    Die Höhe der Pane
     */
    public void adjustGameField(double width, double height) {

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
     */
    public void initImagesGameField(){
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
    }

    /**
     * Intitialisierung der Bilder an den Grenzen des Spielfeldes
     */
    public void initImagesOnBorderGameField(){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        ImageView[][] imageViews = new ImageView[rowsCount][columnsCount];

        for (int row = 0; row < rowsCount; row++){
            for (int column = 0; column < columnsCount; column++){

                // Initialisierung der oberen Grenze
                Image image = new Image(getClass().getResourceAsStream(imagePaths[column]));
                ImageView imageView = new ImageView(image);

                imageViews[0][column] = imageView;
                gridPane.add(imageView, column, 0);
                // else-Bedingung von fitImageViewToBorder
                fitImageViewToBorder(gridPane, imageView, columnsCount, rowsCount, false);


                // Initialisierung der unteren Grenze
                Image image2 = new Image(getClass().getResourceAsStream(imagePaths[column]));
                ImageView imageView2 = new ImageView(image2);

                imageViews[2][column] = imageView2;
                gridPane.add(imageView2, column, 2);
                // else-Bedingung von fitImageViewToBorder
                fitImageViewToBorder(gridPane, imageView2, columnsCount, rowsCount, false);
            }
        }
        initImagesLeftRightBorderGameField();
    }

    /**
     * Initialisierung der Bilder an der linken und rechten Grenze.
     * Wird in initImagesOnBorderGameField verwendet.
     *
     */
    private void initImagesLeftRightBorderGameField(){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        ImageView[][] imageViews = new ImageView[rowsCount][columnsCount];

                imageViews[1][0] = new ImageView();

                Image img = new Image(getClass().getResourceAsStream("tiles/GGGG_ohne_Linien.png"));
                ImageView imageView = new ImageView(img);

                imageViews[1][0] = imageView;
                gridPane.add(imageView, 0, 1);

                fitImageViewToBorder(gridPane, imageView, columnsCount, rowsCount, true);

                imageViews[1][3] = new ImageView();

                Image image = new Image(getClass().getResourceAsStream("tiles/YYYY_ohne_Linien.png"));
                ImageView imageView1 = new ImageView(image);

                imageViews[1][3] = imageView1;
                gridPane.add(imageView1, 3, 1);

                fitImageViewToBorder(gridPane, imageView1, columnsCount, rowsCount, true);
    }

    /**
     * Hilfsmethode um die Bilder automatisch an der Breite und Höhe des GridPanes anzupassen.
     * Die Hilfsmethode soll redundanten Code in den Methoden reduzieren.
     *
     * Die if-Bedingung gilt für die Grenzen links und rechts vom Spielfeld.
     * die else-Bedingung gilt für die Grenzen oben und unten vom Spielfeld.
     *
     * Wird in initImagesLeftRightBorderGameField und initImagesOnBorderGameField verwendet.
     *
     * @param gridPane das GridPane, in dem sich die Bilder befinden
     * @param imageView das ImageView, dessen Größe angepasst werden soll
     * @param columnsCount die Anzahl der Spalten im GridPane.
     * @param rowsCount die Anzahl der Reihen im GridPane.
     */
    private void fitImageViewToBorder(GridPane gridPane, ImageView imageView, int columnsCount, int rowsCount, boolean isLeftRightBorder) {

        if (isLeftRightBorder) {
            imageView.fitWidthProperty().bind(gridPane.widthProperty().
                    divide(columnsCount * columnsCount - 4).subtract(gridPane.getHgap()));
            imageView.fitHeightProperty().bind(gridPane.heightProperty().
                    divide(rowsCount - 1).subtract(gridPane.getHgap()));
        } else {
            imageView.fitWidthProperty().bind(gridPane.widthProperty().
                    divide(columnsCount - 1).subtract(gridPane.getHgap()));
            imageView.fitHeightProperty().bind(gridPane.heightProperty().
                    divide(rowsCount * rowsCount - 1).subtract(gridPane.getVgap()));

        }
    }
}
