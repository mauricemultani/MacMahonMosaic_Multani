package logic;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

/**
 * Klasse für die Drag&Drop Logik und die rotierungs Logik.
 * Die Klasse soll mehrfache Erstellung des gleichen Codes in den GridPanes meiden.
 *
 * @author Maurice Singh Multani
 */
public class TileActions {

    public String getImagePathTile(MosaicTile tile){
        return tile.getImagePath();
    }


    /**
     * Methode, welche das Drag-Verhalten für ein Label
     * mit einem Bild aktiviert.
     *
     * @param gridPane      Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     */
    public static void dragTiles(GridPane gridPane, Label label, ImageView imageView){
        label.setOnDragDetected(mouseEvent -> {
           if (mouseEvent.getButton() == MouseButton.PRIMARY) {
               Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
               ClipboardContent content = new ClipboardContent();
               content.putImage(imageView.getImage());
               db.setContent(content);
               mouseEvent.consume();
           }
        });

        label.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                gridPane.getChildren().remove(label);
            }
        });

        rotateTile(gridPane, label, imageView);
    }

    /**
     * Methode, welche das Drop-Verhalten für eine GridPane aktiviert.
     *
     * @param gridPane      Das GridPane, in dass das Mosaikteil platziert werden darf.
     */
    public static void dropTiles(GridPane gridPane){
        gridPane.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasImage()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        gridPane.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            if (db.hasImage()) {
                boolean dropped = false;
                final double totalWidth = gridPane.getWidth();
                final int columnCount = gridPane.getColumnCount();
                final int rowCount = gridPane.getRowCount();


                double cellWidth = totalWidth / columnCount;
                double x = dragEvent.getX();
                int column = (int) (x / cellWidth);

                if (column >= 0 && column < columnCount) {
                    // Finde die erste freie Zeile in der Spalte
                    for (int row = 0; row < rowCount; row++) {
                        if (isCellEmpty(gridPane, column, row)) {
                            Image image = db.getImage();
                            ImageView imageView = new ImageView(image);

                            imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
                            imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(rowCount));
                            imageView.setPreserveRatio(true);

                            Label droppedLabel = new Label();

                            droppedLabel.setGraphic(imageView);
                            droppedLabel.setUserData(Rotation.DEGREE_0);

                            gridPane.add(droppedLabel, column, row);
                            dragTiles(gridPane, droppedLabel, imageView);
                            dropped = true;
                        }
                    }
                }
            }
            // Bestätigen des Drops
            dragEvent.setDropCompleted(true);
            dragEvent.consume();
        });
    }

    /**
     * Ermöglicht, das Rotieren des Bildes.
     *
     * @param gridPane      Das GridPane, in dass das Mosaikteil platziert werden darf.
     * @param label         Das Label, das rotiert werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragt werden soll.
     */
    private static void rotateTile(GridPane gridPane, Label label, ImageView imageView){
        label.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                Rotation current = (Rotation) label.getUserData();

                if (current == null){
                    current = Rotation.DEGREE_0;
                }
                Rotation next = current.next();


                imageView.setRotate(getDegrees(next));
                fitFieldImageView(gridPane, imageView, next);
                label.setUserData(next);
            }
        });
    }

    private static boolean isCellEmpty(GridPane gridPane, int column, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer col = GridPane.getColumnIndex(node);
            Integer rows = GridPane.getRowIndex(node);

            if (col != null && rows != null && col == column && rows == row) {
                return false; // Zelle ist nicht leer
            }
        }
        return true; // Zelle ist leer
    }

    /**
     * Wandelt die Enum-Werte vom Typ Rotation in Gradangaben.
     * @param rotation  Rotationsrichtung als Enum-Wert.
     * @return          Rotationsrichtung als Winkel in Grad.
     */
    private static double getDegrees(Rotation rotation) {
        return switch (rotation) {
            case DEGREE_90 -> 90;
            case DEGREE_180 -> 180;
            case DEGREE_270 -> 270;
            default -> 0; // Standardwert für DEGREE_0
        };
    }

    /**
     * Anpassen der Mosaikteile an dem Spielfeld.
     * Die Mosaikteile sollen sich mit der Vergrößerung/Verkleinerung des Spielfeldes
     * sich anpassen.
     *
     * @param gridPane  das GridPane, in dass das Mosaikteil platziert werden darf.
     * @param imageView das Bild vom Mosaikteil
     */
    private static void fitFieldImageView(GridPane gridPane, ImageView imageView, Rotation rotation) {
        int columnCount = gridPane.getColumnCount();
        int rowsCount = gridPane.getRowCount();

        if (getDegrees(rotation) == 0 || getDegrees(rotation) == 180) {
            imageView.fitWidthProperty().bind(
                    gridPane.widthProperty().divide(columnCount - 1).
                            subtract(gridPane.getHgap())
            );
            imageView.fitHeightProperty().bind(
                    gridPane.heightProperty().divide(rowsCount - 1).
                            subtract(gridPane.getVgap())
            );
        } else {
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
}
