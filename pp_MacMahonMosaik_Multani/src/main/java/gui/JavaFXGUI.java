package gui;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import logic.GUIConnector;

import java.util.Optional;

/**
 * Verantwortlich für die Änderungen auf der Oberfläche, die durch die Logik initiiert werden.
 * Implementiert das Interface GUIConnector hierzu.
 * Wenn eine Logik erzeugt wird, wird auch gleichermaßen eine JavaFXGUI erstellt und als Parameter an die Logik übergeben.
 *
 * @author Maurice Singh Multani
 */
public class JavaFXGUI implements GUIConnector {

    /**
     * Verschiedene ButtonTypes die in handleClose verwendet werden.
     */
    private final ButtonType buttonSave = new ButtonType("Save");
    private final ButtonType buttonClose = new ButtonType("Close the Game");
    private final ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    private final ButtonType buttonAdjust = new ButtonType("Adjust");

    @Override
    public void gameEnded(boolean gameFinished) {

    }

    /**
     *  Soll bei erfolgreicher Speicherung, dies dem Spieler auch mitteilen.
     */
    @Override
    public void showSuccessSave() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("The Game was successfully saved!");
        alert.setContentText("You may close the Window.");
        alert.showAndWait();
    }

    /**
     * Soll bei einer nicht erfolgreichen Speicherung, dies dem Spieler auch mitteilen.
     */
    @Override
    public void showFailSave() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("The Game was not saved!");
        alert.showAndWait();
    }

    /**
     * Mitteilung beim erfolgreichen Laden eines gespeicherten Spielstands.
     */
    @Override
    public void showSuccessLoad() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("The Game was successfully loaded!");
        alert.setContentText("You may close the Window.");
        alert.showAndWait();
    }

    /**
     * Mitteilung beim fehlerhaften Laden eines gespeicherten Spielstands.
     */
    @Override
    public void showFailLoad() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("There was a fail while loading the Game!");
        alert.setContentText("You may close the Window.");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn der Spieler das Spiel schließen will.
     */
    @Override
    public Optional<ButtonType> showSignWhenHandleClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("MacMahonMosaik");
        alert.setHeaderText("Do you want to save the Game before closing?");

        alert.getButtonTypes().setAll(buttonSave, buttonClose, buttonCancel);

        return alert.showAndWait();
    }

    /**
     * Mitteilung, wenn der Spieler im Editor-Modus sein Board verändern will.
     */
    @Override
    public Optional<Pair<Integer, Integer>> whenChangeSizeOfGameField() {
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setTitle("Changing the Size of your Game field!");
        dialog.setHeaderText("Enter the amount of Columns and Rows your Game field should have");

        dialog.getDialogPane().getButtonTypes().addAll(buttonAdjust, buttonCancel);

        GridPane field = new GridPane();

        TextField rowsTextField = new TextField();
        rowsTextField.setPromptText("Amount of Rows");

        TextField colTextField = new TextField();
        colTextField.setPromptText("Amount of Columns");

        field.add(new Label("Rows: "), 0, 0);
        field.add(rowsTextField, 1, 0);

        field.add(new Label("Columns: "), 0, 1);
        field.add(colTextField, 1, 1);

        dialog.getDialogPane().setContent(field);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonAdjust) {
                try {
                    int rows = Integer.parseInt(rowsTextField.getText());
                    int cols = Integer.parseInt(colTextField.getText());
                    return new Pair<>(rows + 2, cols + 2);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }

    /**
     * Mitteilung, wenn eines der Felder für die Spielfeldgröße leer ist.
     */
    @Override
    public void showMissingNumbersForField() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("Fill out both parameters to change your field.");
        alert.setContentText("Both Parameters must contain numbers between 2 and 6");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn das Spielfeld zu klein ist.
     */
    @Override
    public void showFieldTooSmall() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("The Field must contain a minimum amount of 2 Rows and Columns");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn das Spielfeld zu groß ist.
     */
    @Override
    public void showFieldTooBig() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information!");
        alert.setHeaderText("The Maximum Amount of Rows and Columns is 6");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn der Editor nicht aktiviert ist.
     */
     @Override
    public void showEditorNotActive() {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information!");
         alert.setHeaderText("The Editor is not set active!");
         alert.setContentText("Activate the Editor Mode to use the functions!");
         alert.showAndWait();
    }


    /**
     * Mitteilung, wenn Löcher zu platzieren sind.
     */
    @Override
    public void showHolesToBePlaced(int numHoles) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("There are more than 24 cells on the field!");
        alert.setContentText("Place " + numHoles + " holes on the field\n"
        + "After placing the holes you will be able to continue the Game");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn alle Löcher platziert sind.
     */
    @Override
    public void showAllHolesPlaced() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("All Holes are placed!\n"
        + "You can continue with the Game.");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn nur ein Loch zu platzieren ist.
     */
    @Override
    public void showOnlyOneHoleToPlace() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("There are more than 24 cells on the field!\n"
                + "Place one Hole.");
        alert.setContentText("After placing this Hole you will be able to continue the Game.");
        alert.showAndWait();
    }
}
