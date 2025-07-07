package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
    public final ButtonType buttonSave = new ButtonType("Save");
    public final ButtonType buttonClose = new ButtonType("Close the Game");
    public final ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

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
        alert.setContentText("You may close the Window.");
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

}
