package gui;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import logic.Rotation;

/**
 * Klasse für die Drag&Drop Logik und die rotierungs Logik.
 * Die Klasse soll mehrfache Erstellung des gleichen Codes in den GridPanes meiden.
 *
 * @author Maurice Singh Multani
 */
public class TileActions {

    /**
     * Methode, welche das Drag-Verhalten für ein Label
     * mit einem Bild aktiviert.
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

        rotateTile(label, imageView);
    }

    /**
     * Ermöglicht, das Rotieren des Bildes.
     * @param label         Das Label, das rotiert werden soll.
     * @param imageView     Das ImageView, dessen Bild übertragt werden soll.
     */
    public static void rotateTile(Label label, ImageView imageView){
        label.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                Rotation current = (Rotation) label.getUserData();

                if (current == null){
                    current = Rotation.DEGREE_0;
                }
                Rotation next = current.next();

                imageView.setRotate(getDegrees(next));
                label.setUserData(next);
            }
        });
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
}
