package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.util.Random;

/**
 * Steuerung des Spielfeldes.
 *
 * @author Maurice Singh Multani
 */
public class GameFieldController extends Canvas {

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
     * String welches die Randbilder enthält.
     * Werden verwendet um die Ränder vom Bild darzustellen.
     */
    String[] borderImages = {
            "/gui/tiles/YYYY_ohne_Linien.png",
            "/gui/tiles/GGGG_ohne_Linien.png",
            "/gui/tiles/RRRR_ohne_Linien.png",
    };




    /**
     * Anpassen der Größe des Spielfeldes mit der Anpassung,
     * dass die Randzellen um 1/4 kleiner als die Spielfeldzellen sind.
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
     * Anpassen der mittleren Zellen des Spielfeldes.
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

                imageViews[row][column].setImage(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
                gridPane.add(imageViews[row][column], column + 1, row + 1);


                imageViews[row][column].fitWidthProperty().bind(gridPane.widthProperty().
                        divide(columnsCount - 1).subtract(gridPane.getHgap()));
                imageViews[row][column].fitHeightProperty().bind(gridPane.heightProperty().
                        divide(rowsCount - 1).subtract(gridPane.getVgap()));
            }
        }
    }

    /**
     * Initialisierung der Bilder an den Grenzen des Spielfeldes.
     */
    public void initImagesBorderGameField(){
        initImagesColumnBorder();
        initImagesRowBorder();
    }

    /**
     * Initialisierung der Bilder an der linken und rechten Grenze vom Spielfeld.
     * Wird in initImagesBorderGameField verwendet.
     *
     */
    private void initImagesColumnBorder(){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        Random random = new Random();
        ImageView[][] imageViews = new ImageView[rowsCount - 1][1];

                for (int row = 1; row < rowsCount - 1; row++){

                    imageViews[row][0] = new ImageView();

                    String RandomColor = borderImages[random.nextInt(borderImages.length)];
                    Image image = new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png"));
                    ImageView leftBorderImages = new ImageView(image);
                    ImageView rightBorderImages = new ImageView(image);

                    gridPane.add(leftBorderImages, 0, row);
                    gridPane.add(rightBorderImages, columnsCount - 1, row);

                    fitImageViewToBorder(rightBorderImages, true);
                    fitImageViewToBorder(leftBorderImages, true);
                }
    }

    /**
     * Initialisierung der Bilder an der oberen und unteren Grenze vom Spielfeld.
     * Wird in initImagesBorderGameField verwendet.
     */
    private void initImagesRowBorder(){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        Random random = new Random();
        ImageView[][] imageViews = new ImageView[1][columnsCount - 1];

        for (int column = 1; column < columnsCount - 1; column++){
            imageViews[0][column] = new ImageView();

            String RandomColor = borderImages[random.nextInt(borderImages.length)];
            Image image = new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png"));

            ImageView topBorderImages = new ImageView(image);
            ImageView bottomBorderImage = new ImageView(image);

            gridPane.add(topBorderImages, column, 0);
            gridPane.add(bottomBorderImage, column, rowsCount - 1);

            fitImageViewToBorder(topBorderImages, false);
            fitImageViewToBorder(bottomBorderImage, false);
        }
    }

    /**
     * Hilfsmethode um die Bilder automatisch an der Breite und Höhe des GridPanes anzupassen.
     * Die Hilfsmethode soll redundanten Code in den Methoden reduzieren.
     *
     * Die if-Bedingung gilt für die Grenzen links und rechts vom Spielfeld.
     * die else-Bedingung gilt für die Grenzen oben und unten vom Spielfeld.
     *
     * Wird in initImagesColumnBorder und initImagesRowBorder verwendet.
     *
     * @param imageView das ImageView, dessen Größe angepasst werden soll
     * @param isColumnBorder Boolescher Wert um die Bedingungen klarzustellen.
     *                       Wenn true, dann handelt es sich um die Grenzen links und rechts vom Spielfeld.
     *                       Wenn false, dann handelt es sich um die Grenzen oben und unten vom Spielfeld.
     */
    private void fitImageViewToBorder(ImageView imageView, boolean isColumnBorder) {
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        if (isColumnBorder) {
            imageView.fitWidthProperty().bind(gridPane.widthProperty().
                    divide(columnsCount * 3.25));
            imageView.fitHeightProperty().bind(gridPane.heightProperty().
                    divide(rowsCount - 1));
        } else {
            imageView.fitWidthProperty().bind(gridPane.widthProperty().
                    divide(columnsCount - 1));
            imageView.fitHeightProperty().bind(gridPane.heightProperty().
                    divide(rowsCount * 3.25));

        }
    }

    /**
     * Die Methode soll für die Ränder des Spielfeldes zufällige Farben (Gelb, Grün und Rot) generieren.
     */
    public void randomColorsForBorder(){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        Random random = new Random();
        ImageView[][] imageViews = new ImageView[rowsCount][columnsCount];

        for (int row = 0; row < rowsCount; row++){
            for (int column = 0; column < columnsCount; column++){

                if ((row == 0) || (row == rowsCount - 1) || (column == 0) || (column == columnsCount -1)){
                    String RandomColor  = borderImages[random.nextInt(borderImages.length)];
                    Image borderImage = new Image(getClass().getResourceAsStream(RandomColor));
                    ImageView imageView = new ImageView(borderImage);
                    imageView.setImage(borderImage);
                    // fitImageViewToBorder(gridPane, imageView, columnsCount, rowsCount, true);
                }
            }
        }
    }
}
