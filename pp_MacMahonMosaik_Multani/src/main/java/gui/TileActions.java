package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
     * @param board         Das Spielfeld das vom GridPane dargestellt wird.
     * @param label         Das Label, das verschoben werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragen wird.
     * @param pos           Die Position
     * @param tile          Das Mosaikteil
     * @param rotation      Die Rotation
     */
    public static void boardActions(GridPane gridPane, Board board, Label label, ImageView imageView, Position pos, MosaicTile tile, Rotation rotation) {
        dragTiles(label, imageView, tile);

        boardOnDragDone(gridPane, board, label, pos);

        rotateTile(gridPane, label, imageView, pos, board, tile);

        differentColorsOnEdge(board, tile, rotation, pos, imageView);

        fitBoardImageView(gridPane, imageView);
    }

    /**
     * Die Methode beschränkt sich auf alle Aktionen, die im gridBottom gridPane gemacht werden dürfen.
     *
     * @param gridPane  Das GridPane, von dem die Mosaikteile gedragged werden können.
     * @param label     Das Label, das verschoben werden soll.
     * @param imageView Das ImageView, dessen Bild übertragen wird.
     * @param tile      Das Mosaikteil
     */
    public static void gridBottomActions(GridPane gridPane, Label label, ImageView imageView, MosaicTile tile) {
        dragTiles(label, imageView, tile);

        gridBottomOnDragDone(gridPane, label);

        dropTiles(gridPane);
    }

    /**
     * Methode, welche das Drag-Verhalten für ein Label
     * mit einem Bild aktiviert.
     *
     * @param label     Das Label, das verschoben werden soll.
     * @param imageView Das ImageView, dessen Bild übertragen wird.
     * @param tile      Das Mosaikteil
     */
    private static void dragTiles(Label label, ImageView imageView, MosaicTile tile) {
        label.setOnDragDetected(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                Dragboard db = label.startDragAndDrop(TransferMode.MOVE);

                ImageView rotatedView = new ImageView(imageView.getImage());
                Rotation rotation = (Rotation) label.getUserData();

                if (rotation == null) rotation = Rotation.DEGREE_0;

                rotatedView.setRotate(TileActions.getDegrees(rotation));

                rotatedView.setFitWidth(imageView.getFitWidth());
                rotatedView.setFitHeight(imageView.getFitHeight());

                ClipboardContent content = new ClipboardContent();
                content.putImage(imageView.getImage());

                // Das Teil wird in einem String mit ihrer Rotation gespeichert.
                String data = tile.name() + '/' + rotation.name();

                content.putString(data);

                db.setContent(content);
                mouseEvent.consume();

                imageView.setEffect(null);
                imageView.setBlendMode(null);
            }
        });
    }

    /**
     * Private Methode, wenn im board ein DragDone vorkommt.
     * Entfernt auch die Position vom Board.
     *
     * @param gridPane das GridPane
     * @param board    das Spielfeld
     * @param label    das Label
     * @param pos      die Position
     */
    private static void boardOnDragDone(GridPane gridPane, Board board, Label label, Position pos) {
        label.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                board.removeTileAt(pos);

                gridPane.getChildren().remove(label);
            }
        });
    }

    /**
     * Private Methode, wenn im gridBottom ein DragDone benötigt wird.
     *
     * @param gridPane das GridPane
     * @param label    das Label
     */
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
     * @param gridPane Das GridPane, in dass das Mosaikteil platziert werden darf.
     */
    private static void dropTiles(GridPane gridPane) {
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
                final double totalHeight = gridPane.getHeight();

                final int columnCount = gridPane.getColumnCount();
                final int rowsCount = gridPane.getRowCount();

                double cellWidth = totalWidth / columnCount;
                double cellHeight = totalHeight / rowsCount;

                double x = dragEvent.getX();
                double y = dragEvent.getY();

                int column = (int) (x / cellWidth);
                int row = (int) (y / cellHeight);

                if (isCellNotEmpty(gridPane, column, row)) {
                    dragEvent.setDropCompleted(false);
                    dragEvent.consume();
                    return;
                }

                if (column >= 0 && column < columnCount && row >= 0 && row < rowsCount) {
                    String[] tileInfo = db.getString().split("/");
                    MosaicTile tile = MosaicTile.valueOf(tileInfo[0]);
                    Rotation rotation = Rotation.valueOf(tileInfo[1]);

                    ImageView imageView = new ImageView(db.getImage());
                    imageView.setRotate(getDegrees(rotation));

                    imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(columnCount));
                    imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(rowsCount));
                    imageView.setPreserveRatio(false);

                    Label droppedLabel = new Label();
                    droppedLabel.setGraphic(imageView);
                    droppedLabel.setUserData(rotation);

                    gridPane.add(droppedLabel, column, row);
                    gridBottomActions(gridPane, droppedLabel, imageView, tile);

                    imageView.setEffect(null);
                    imageView.setBlendMode(null);
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
     * @param label     Das Label, das rotiert werden soll.
     * @param imageView Das ImageView, dessen Bild übertragt werden soll.
     */
    private static void rotateTile(GridPane gridPane, Label label, ImageView imageView, Position pos, Board board, MosaicTile tile) {
        label.setOnMouseClicked(mouseEvent -> {
            // rotiert nur bei Rechtsklick
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                // holt sich den derzeitigen Rotierungsgrad
                Rotation current = (Rotation) label.getUserData();

                // Wenn die derzeitige Rotierung == null ist,
                // dann wird sie auf 0-Grad gesetzt.
                if (current == null) {
                    current = Rotation.DEGREE_0;
                }
                // andernfalls geht sie zur nächsten rotierung rüber.
                Rotation next = current.next();
                fitBoardImageView(gridPane, imageView);

                // das imageView und auch das label werden auf die nächste Rotation gesetzt.
                imageView.setRotate(getDegrees(next));
                label.setUserData(next);

                board.placeTileAt(tile, next, pos);
                differentColorsOnEdge(board, tile, next, pos, imageView);
            }
        });
    }

    /**
     * Private Methode welche prüft, ob eine Zelle leer ist, oder nicht.
     *
     * @param gridPane das Spielfeld.
     * @param column   Spalte bzw. Spaltenanzahl
     * @param row      Reihe bzw. Reihenanzahl
     * @return false, wenn eine leere Zelle gefunden wird, ansonsten true.
     */
    public static boolean isCellNotEmpty(GridPane gridPane, int column, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer col = GridPane.getColumnIndex(node);
            Integer rows = GridPane.getRowIndex(node);

            if (col != null && rows != null && col == column && rows == row) {
                return true; // Zelle ist nicht leer
            }
        }
        return false; // Zelle ist leer
    }

    /**
     * Wandelt die Enum-Werte vom Typ Rotation in Gradangaben.
     *
     * @param rotation Rotationsrichtung als Enum-Wert.
     * @return Rotationsrichtung als Winkel in Grad.
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
     * Die Methode soll bewirken, dass wenn ein Mosaikteil falsch platziert ist,
     * das Mosaikteil leicht "glühen" sollte, damit dem Spieler klar wird,
     * dass das Mosaikteil falsch platziert ist.
     */
    public static void differentColorsOnEdge(Board board, MosaicTile tile, Rotation rotation, Position pos, ImageView imageView) {
        if (!board.fitsNeighbours(tile, rotation, pos)) {
            imageView.setEffect(new InnerShadow(0.1, Color.RED));
            imageView.setBlendMode(BlendMode.GREEN);
        } else if (board.fitsNeighbours(tile, rotation, pos)) {
            imageView.setEffect(null);
            imageView.setBlendMode(null);
        }
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
     * @param x - Spalte, in die das Bild eingefügt wird
     * @param y - Reihe, in die das Bild eingefügt wird
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
