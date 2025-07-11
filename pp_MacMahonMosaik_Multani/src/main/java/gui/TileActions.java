package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import logic.Board;
import logic.MosaicTile;
import logic.Position;
import logic.Rotation;

/**
 * Klasse für die Drag&Drop Logik und die rotierungs Logik.
 * Die Klasse soll mehrfache Erstellung des gleichen Codes in den GridPanes meiden.
 *
 * @author Maurice Singh Multani
 */
public class TileActions {

    /**
     * Die Methode beschränkt sich auf alle Aktionen, die im Spielfeld gemacht werden dürfen.
     * @param gridPane      Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param tile          Das Mosaikteil
     */
    public static void boardActions(GridPane gridPane, Board board, Label label, ImageView imageView, Position pos, MosaicTile tile) {
        dragTiles(label, imageView, tile);

        boardOnDragDone(gridPane, board, label, pos);

        rotateTile(gridPane, label, imageView);

        fitBoardImageView(gridPane, imageView);
    }

    /**
     * Die Methode beschränkt sich auf alle Aktionen, die im gridBottom gridPane gemacht werden dürfen.
     * @param gridPane      Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param tile          Das Mosaikteil
     */
    public static void gridBottomActions(GridPane gridPane,Label label, ImageView imageView, MosaicTile tile) {
        dragTiles(label, imageView, tile);

        gridBottomOnDragDone(gridPane, label);

        dropTiles(gridPane);
    }

    /**
     * Methode, welche das Drag-Verhalten für ein Label
     * mit einem Bild aktiviert.
     *
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param tile          Das Mosaikteil
     */
    private static void dragTiles(Label label, ImageView imageView, MosaicTile tile){
        label.setOnDragDetected(mouseEvent -> {
           if (mouseEvent.getButton() == MouseButton.PRIMARY) {
               Dragboard db = label.startDragAndDrop(TransferMode.MOVE);

               //TODO: gedrehtes Bild übertragen und nicht das Standardbild.
               ImageView rotatedView = new ImageView(imageView.getImage());
               // Rotation rotation = (Rotation) label.getUserData();

               // if (rotation == null) rotation = Rotation.DEGREE_0;

               // rotatedView.setRotate(TileActions.getDegrees(rotation));

               rotatedView.setFitWidth(imageView.getFitWidth());
               rotatedView.setFitHeight(imageView.getFitHeight());

               ClipboardContent content = new ClipboardContent();
               content.putImage(imageView.getImage());

               String data = tile.name();
                       // + '/' + rotation.name();

               content.putString(data);

               db.setContent(content);
               mouseEvent.consume();
           }
        });
    }

    private static void boardOnDragDone(GridPane gridPane, Board board, Label label, Position pos) {
        label.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                gridPane.getChildren().remove(label);

                board.removeTileAt(pos);
            }
        });
    }

    private static void gridBottomOnDragDone(GridPane gridPane, Label label) {
        label.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                gridPane.getChildren().remove(label);
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

                            // String[] tileInfo = db.getString().split("/");
                            // MosaicTile tile = MosaicTile.valueOf(tileInfo[0]);
                            // Rotation rotation = Rotation.valueOf(tileInfo[1]);

                            String tileInfo = db.getString();
                            MosaicTile tile = MosaicTile.valueOf(tileInfo);

                            ImageView imageView = new ImageView(db.getImage());

                            // imageView.setRotate(getDegrees(rotation));

                            imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
                            imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(rowCount));
                            imageView.setPreserveRatio(true);

                            Label droppedLabel = new Label();

                            droppedLabel.setGraphic(imageView);
                            // droppedLabel.setUserData(rotation);

                            gridPane.add(droppedLabel, column, row);
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
    private static void rotateTile(GridPane gridPane, Label label, ImageView imageView){
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
                fitBoardImageView(gridPane, imageView);

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
     */
    private static void fitBoardImageView(GridPane gridPane, ImageView imageView) {
        int columnCount = gridPane.getColumnCount();
        int rowsCount = gridPane.getRowCount();

        imageView.fitWidthProperty().bind(
                gridPane.widthProperty().divide(columnCount - 1).
                        subtract(gridPane.getHgap())
        );
        imageView.fitHeightProperty().bind(
                gridPane.heightProperty().divide(rowsCount - 1).
                        subtract(gridPane.getVgap())
        );
    }

    /**
     * Erstellt ein ImageView für ein darzustellendes Bild und fügt
     * es der GridPane zu. Um einen JavaFX-Bug bei der Rotation zu kompensieren,
     * wird das ImageView einer StackPane zugefügt, die das Bild zentriert, und die
     * StackPane wiederum einem RotatablePaneLayouter, der die fehlerhafte
     * Rotation überschreibt.
     *
     * @param imageViewWidth - initiale Breite des ImageViews
     * @param imageViewHeight - initiale Höhe des ImageViews
     * @param x - Spalte, in die das Bild eingefügt wird
     * @param y - Reihe, in die das Bild eingefügt wird
     * @param grdPn - GridPane, der das Bild zugefügt wird
     * @return das eingebettete ImageView
     */
    private static void createRotatableImageView(GridPane gridPane, Image image, int x, int y){
        // neues ImageView
        ImageView imageView = new ImageView();

        // Bild soll an die Zelle angepasst sein und Ratio nicht behalten
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);

        // Neues StackPane, um das Bild zu zentrieren
        StackPane stackpane = new StackPane(imageView);

        // ImageView Maße werden an StackPane Maße gebunden
        imageView.fitWidthProperty().bind(stackpane.widthProperty());
        imageView.fitHeightProperty().bind(stackpane.heightProperty());

        RotatablePaneLayouter rotatableContainer = new RotatablePaneLayouter(stackpane);

        gridPane.add(rotatableContainer, x, y);

        rotatableContainer.setUserData(Rotation.DEGREE_0);

        rotatableContainer.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                // holt sich den derzeitigen Rotierungsgrad
                Rotation current = (Rotation) rotatableContainer.getUserData();

                // Wenn die derzeitige Rotierung == null ist,
                // dann wird sie auf 0-Grad gesetzt.
                if (current == null){
                    current = Rotation.DEGREE_0;
                }
                // andernfalls geht sie zur nächsten rotierung rüber.
                Rotation next = current.next();

                rotatableContainer.setRotate(getDegrees(next));
                rotatableContainer.setUserData(next);
            }
        });
    }
}
