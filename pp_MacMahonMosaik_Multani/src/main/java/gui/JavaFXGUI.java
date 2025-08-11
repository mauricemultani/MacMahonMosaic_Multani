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
     * Verschiedene ButtonTypes die in showSignWhenHandleClose
     * und whenChangingSizeOfGameField verwendet werden.
     */
    private final ButtonType buttonSave = new ButtonType("Save");
    private final ButtonType buttonClose = new ButtonType("Close the Game");
    private final ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    private final ButtonType buttonAdjust = new ButtonType("Adjust");

    /**
     * Mitteilung, welche bei einem gewonnenen Spiel kommen soll.
     */
    @Override
    public void showGameWon() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game is solved!");
        alert.setHeaderText("Congratulations! You correctly solved the puzzle!");
        alert.setContentText("You may choose to rerun a new Game for another Game of fun!");
        alert.showAndWait();
    }

    /**
     * Mitteilung, welche bei einem nicht erfolgreichen Spiel kommen soll.
     */
    @Override
    public void showGamesNotFinished() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game is unfinished");
        alert.setHeaderText("This may come in unhandy, but the Board is not correctly solved...");
        alert.setContentText("Check the placed Tiles, there may be some wrong placements.");
        alert.showAndWait();
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
        alert.setContentText(" 1. Please only load json-Files \n" +
                " 2. Only load Boards within the allowed range of 2x2 to 6x6");
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
    public Optional<Pair<Integer, Integer>> whenChangingSizeOfGameField() {
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
         alert.setContentText("Activate the Editor Mode to use its functions!");
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
        alert.setTitle("Too many empty Cells!");
        alert.setHeaderText("There are more than 24 cells on the field!\n"
                + "Place one Hole.");
        alert.setContentText("After placing this Hole you will be able to continue the Game.");
        alert.showAndWait();
    }

    /**
     * Diese Mitteilung soll erscheinen, wenn der Spieler vom Editor-Modus
     * in den Spielmodus zurückkehren will, aber noch nicht die Bedingungen erfüllt hat.
     */
    @Override
    public void showHolesNotPlaced_Editor() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Conditions to be in the Game are not met");
        alert.setHeaderText(" You need to place enough holes to switch to the Game Mode");
        alert.setContentText(" That is because you only have 24 Mosaictiles but more empty cells");
        alert.showAndWait();
    }

    /**
     * Diese Mitteilung soll erscheinen, wenn der Spieler im Editormodus
     * ein Spiel speichern will, aber noch keine Löcher platziert hat.
     */
    @Override
    public void showHolesNotPlaced_Save() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Conditions to save the Game are not met");
        alert.setHeaderText(" You need to place your holes to save the Game");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn der Spieler versucht, ein Loch
     * in einem schon vorhandenen Loch in Lochsetzung in Spielfeldgrößenänderung zu setzen.
     */
    @Override
    public void showPlacingHoleNotAllowed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Placing a hole on an already set hole?!");
        alert.setHeaderText(" You cannot place a hole on an already placed hole.");
        alert.setContentText(" Pointing out the obvious...");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn kein platzierbares Mosaikteil gefunden wurde.
     */
    @Override
    public void showNoPlaceableTileFound() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("It seems as if there is no Tile placeable.");
        alert.setContentText("Maybe rearrange some Tiles differently.");
        alert.showAndWait();
    }

    /**
     * Mitteilung, wenn es eine mögliche Lösung beim derzeitigen Spielfeld gibt.
     */
    @Override
    public void showPossibleSolvation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("The current field is solvable!");
        alert.setContentText("Have fun struggling and solving :)");
        alert.showAndWait();
    }

    /**
     * Mitteilung, welche kommen soll, wenn beim Verlassen des Editormodus in den Spielmodus,
     * vom Programm festgestellt wird, dass mit dem dargestellten Spielfeld keine mögliche Lösung möglich ist.
     */
    @Override
    public void showNoPossibleSolvation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("It seems as if the field is not solvable.");
        alert.setContentText("Load the Game in Editor to change the colors of your borders \n" +
                "or rearrange some Tiles for a possible Solvation.");
        alert.showAndWait();
    }

    /**
     * Mitteilung, dass die Lösbarkeitsprüfung übersprungen wird,
     * da es ansonsten zu lange dauern würde.
     */
    @Override
    public void showSkipSolvabilityCheck() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("The Solvability check is being skipped. \n" +
                "You might play an unsolvable Board.");
        alert.setContentText("""
                Else the programm would take too long to run.\s
                The Solvability check will work once \s
                there are 18 empty Cells left.""");
        alert.showAndWait();
    }

    /**
     * Mitteilung, dass die "Help Me!"-Funktion nicht funktionieren wird.
     * Sie soll erst funktionieren, wenn maximal 18 leere Zellen noch zur Verfügung stehen.
     */
    @Override
    public void showSkipHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("The Help Option will work once there are only 18 empty cells left.");
        alert.setContentText("Else the programm would take too long to run. \n" +
                "Thank you for your understanding.");
        alert.showAndWait();
    }

    /**
     * Mitteilung, welche dann erscheint wenn die "Help Me!" Funktion
     * im Editormodus verwendet wird.
     */
    @Override
    public void showNotAvailableInEditor() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("This function is not available in Editor");
        alert.setContentText("Solving the puzzle should only happen in Game");
        alert.showAndWait();
    }
}
