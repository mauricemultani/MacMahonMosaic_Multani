package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import logic.MosaicTile;
import logic.Rotation;

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
     * Die Methode beschränkt sich auf alle Aktionen, die im Spielfeld gemacht werden dürfen.
     * @param gridPane      Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param tile          Das Mosaikteil
     * @param rotation      Die Rotation vom Mosaikteil
     */
    public static void boardActions(GridPane gridPane, Label label, ImageView imageView, MosaicTile tile, Rotation rotation) {
        dragTiles(gridPane, label, imageView, tile);

        rotateTile(label, imageView);

        fitBoardImageView(gridPane, imageView, rotation);
    }

    /**
     * Die Methode beschränkt sich auf alle Aktionen, die im gridBottom gridPane gemacht werden dürfen.
     * @param gridPane      Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param tile          Das Mosaikteil
     */
    public static void gridBottomActions(GridPane gridPane, Label label, ImageView imageView, MosaicTile tile) {
        dragTiles(gridPane, label, imageView, tile);

        dropTiles(gridPane);
    }

    /**
     * Methode, welche das Drag-Verhalten für ein Label
     * mit einem Bild aktiviert.
     *
     * @param gridPane      Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param tile          Das Mosaikteil
     */
    private static void dragTiles(GridPane gridPane, Label label, ImageView imageView, MosaicTile tile){
        label.setOnDragDetected(mouseEvent -> {
           if (mouseEvent.getButton() == MouseButton.PRIMARY) {
               Dragboard db = label.startDragAndDrop(TransferMode.MOVE);

               //TODO: gedrehtes Bild übertragen und nicht das Standardbild.
               ClipboardContent content = new ClipboardContent();
               content.putImage(imageView.getImage());

               Rotation rotation = (Rotation) label.getUserData();
               if (rotation == null) {
                   rotation = Rotation.DEGREE_0;
               }

               String data = tile.name() + '/' + rotation.name();

               content.putString(data);

               db.setContent(content);
               mouseEvent.consume();
               gridPane.getChildren().remove(label);
           }
        });

        label.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                gridPane.getChildren().remove(label);
            } else {

                gridPane.add(label, c);
            }
        });

    }

    /**
     * Methode, welche das Drop-Verhalten für eine GridPane aktiviert.
     *
     * @param gridPane      Das GridPane, in dass das Mosaikteil platziert werden darf.
     */
    private static void dropTiles(GridPane gridPane){
        gridPane.setOnDragOver(dragEvent -> {
            if (dragEvent.getDragboard().hasString()) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
            dragEvent.consume();
        });

        gridPane.setOnDragDropped(dragEvent -> {
            Dragboard db = dragEvent.getDragboard();
            if (db.hasString()) {
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

                            String[] tileInfo = db.getString().split("/");
                            MosaicTile tile = MosaicTile.valueOf(tileInfo[0]);
                            Rotation rotation = Rotation.valueOf(tileInfo[1]);

                            ImageView imageView = new ImageView(db.getImage());

                            imageView.setRotate(getDegrees(rotation));

                            imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
                            imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(rowCount));
                            imageView.setPreserveRatio(true);

                            Label droppedLabel = new Label();

                            droppedLabel.setGraphic(imageView);
                            droppedLabel.setUserData(rotation);

                            gridPane.add(droppedLabel, column, row);
                            dragTiles(gridPane, droppedLabel, imageView, tile);
                        }
                        else if (!isCellEmpty(gridPane, column, row)) {
                            dragEvent.setDropCompleted(false);
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
     * @param label         Das Label, das rotiert werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragt werden soll.
     */
    private static void rotateTile(Label label, ImageView imageView){
        label.setOnMouseClicked(mouseEvent -> {
            // rotiert nur bei Rechtsklick
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                // holt sich den derzeitigen Rotierungsgrad
                Rotation current = (Rotation) label.getUserData();

                // Wenn die derzeitige Rotierung == null ist,
                // dann wird sie auf 0-Grad gesetzt.
                if (current == null){
                    current = Rotation.DEGREE_0;
                }
                // andernfalls geht sie zur nächsten rotierung rüber.
                Rotation next = current.next();

                // das imageView und auch das label werden auf die nächste Rotation gesetzt.
                imageView.setRotate(getDegrees(next));
                label.setUserData(next);
            }
        });
    }

    /**
     * Private Methode welche prüft, ob eine Zelle leer ist, oder nicht.
     *
     * @param gridPane  das Spielfeld.
     * @param column    Spalte bzw. Spaltenanzahl
     * @param row       Reihe bzw. Reihenanzahl
     * @return          true, wenn eine leere Zelle gefunden wird, ansonsten false.
     */
    public static boolean isCellEmpty(GridPane gridPane, int column, int row) {
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
    public static double getDegrees(Rotation rotation) {
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
     * @param imageView das Bild vom Mosaikteil.
     * @param rotation  Rotation vom Bild.
     */
    private static void fitBoardImageView(GridPane gridPane, ImageView imageView, Rotation rotation) {
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
}
