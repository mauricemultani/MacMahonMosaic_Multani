package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import logic.MosaicTile;
import logic.Rotation;

import java.util.Objects;
import java.util.Random;

/**
 * Steuerung des Spielfeldes.
 *
 * @author Maurice Singh Multani
 */
public class BoardController {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    private final GridPane gridPane;

    private final TileActions tileActions = new TileActions();

    private final Random random = new Random();

    /**
     * Konstruktor, welches ein GameFieldController mit einem GridPane initialisiert.
     * @param gridPane das GridPane, was mit dem GameFieldController initialisiert wird.
     */
    public BoardController(GridPane gridPane){
        this.gridPane = gridPane;
    }

    /**
     * String welches die Randbilder enthält.
     * Werden verwendet, um die Ränder vom Bild darzustellen.
     */
    String[] borderImages = {
            "/gui/tiles/GGGG_ohne_Linien.png",
            "/gui/tiles/RRRR_ohne_Linien.png",
            "/gui/tiles/YYYY_ohne_Linien.png"
    };

    public void displayTile(MosaicTile tile){
        String imagePath = tileActions.getImagePathTile(tile);
    }

    /**
     * Anpassen der Größe des Spielfeldes mit der Anpassung,
     * dass die Randzellen um 1/4 kleiner als die Spielfeldzellen sind.
     */
    public void adjustRowsAndColumnsGameField(){
        final int cols = gridPane.getColumnCount();
        final int rows = gridPane.getRowCount();
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
     * Anpassung der GridLines beim Spielfeld.
     * Implementierung fehlt noch.
     */
    private void adjustGridLines(){

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

                imageViews[row][column].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/gui/tiles/NNNN.png"))));
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

            for (int row = 1; row < rowsCount - 1; row++){
                String RandomColor = borderImages[random.nextInt(borderImages.length)];
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(String.valueOf(RandomColor))));

                ImageView leftBorderImages = new ImageView(image);
                gridPane.add(leftBorderImages, 0, row);
                fitBorderImageView(leftBorderImages, true);
            }

            for (int row = 1; row < rowsCount - 1; row++){
                String RandomColor = borderImages[random.nextInt(borderImages.length)];
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(String.valueOf(RandomColor))));

                ImageView rightBorderImages = new ImageView(image);
                gridPane.add(rightBorderImages, columnsCount - 1, row);
                fitBorderImageView(rightBorderImages, true);
            }
    }

    /**
     * Initialisierung der Bilder an der oberen und unteren Grenze vom Spielfeld.
     * Wird in initImagesBorderGameField verwendet.
     */
    private void initImagesRowBorder(){
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        for (int column = 1; column < columnsCount - 1; column++){
            String RandomColor = borderImages[random.nextInt(borderImages.length)];
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RandomColor)));

            ImageView topBorderImages = new ImageView(image);
            gridPane.add(topBorderImages, column, 0);
            fitBorderImageView(topBorderImages, false);
        }

        for (int column = 1; column < columnsCount - 1; column++){
            String RandomColor = borderImages[random.nextInt(borderImages.length)];
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(RandomColor)));

            ImageView bottomBorderImage = new ImageView(image);
            gridPane.add(bottomBorderImage, column, rowsCount - 1);
            fitBorderImageView(bottomBorderImage, false);
        }
    }



    /**
     * Methode zum droppen der Mosaikteile.
     */
    public void dropTiles(){
        gridPane.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasImage()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        gridPane.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            if (db.hasImage()) {
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

                if (x < borderWidth || x > totalWidth - borderWidth ||
                        y < borderHeight || y > totalHeight - borderHeight){
                    dragEvent.setDropCompleted(false);
                    dragEvent.consume();
                    return;
                }

                if (column >= 0 && column < middleCols && row >= 0 && row < middleRows) {
                    ImageView imageView = new ImageView(db.getImage());
                    fitFieldImageView(imageView);

                    Label droppedLabel = new Label();
                    droppedLabel.setGraphic(imageView);
                    droppedLabel.setUserData(Rotation.DEGREE_0);

                    gridPane.add(droppedLabel, column + 1, row + 1);
                    TileActions.dragTiles(gridPane, droppedLabel, imageView);
                }
            }
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });
    }

    /**
     * Anpassen der Mosaikteile an dem Spielfeld.
     * Die Mosaikteile sollen sich mit der Vergrößerung/Verkleinerung des Spielfeldes
     * sich anpassen.
     * @param imageView das Bild vom Mosaikteil
     */
    private void fitFieldImageView(ImageView imageView) {
        int columnCount = gridPane.getColumnCount();
        int rowsCount = gridPane.getRowCount();

        double cellWidth = (gridPane.getWidth() / (columnCount - 1)) - gridPane.getHgap();
        double cellHeight = (gridPane.getHeight() / (rowsCount - 1)) - gridPane.getVgap();

        imageView.setFitWidth(cellWidth);
        imageView.setFitHeight(cellHeight);
    }

    /**
     * Hilfsmethode um die Bilder automatisch an der Breite und Höhe des GridPanes anzupassen.
     * Die Hilfsmethode soll redundanten Code in den Methoden reduzieren.
     * <p>
     * Die if-Bedingung gilt für die Grenzen links und rechts vom Spielfeld.
     * die else-Bedingung gilt für die Grenzen oben und unten vom Spielfeld.
     * <p>
     * Wird in initImagesColumnBorder und initImagesRowBorder verwendet.
     *
     * @param imageView das ImageView, dessen Größe angepasst werden soll
     * @param isColumnBorder Boolescher Wert um die Bedingungen klarzustellen.
     *                       Wenn true, dann handelt es sich um die Grenzen links und rechts vom Spielfeld.
     *                       Wenn false, dann handelt es sich um die Grenzen oben und unten vom Spielfeld.
     */
    private void fitBorderImageView(ImageView imageView, boolean isColumnBorder) {
        final int columnsCount = gridPane.getColumnCount();
        final int rowsCount = gridPane.getRowCount();

        // Herausfinden, weshalb 3.25 richtig ist und die Berechnung aktualisieren
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
}
