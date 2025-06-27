package gui;

import javafx.scene.control.Alert;
import logic.GUIConnector;

/**
 * Verantwortlich für die Änderungen auf der Oberfläche, die durch die Logik initiiert werden.
 * Implementiert das Interface GUIConnector hierzu.
 * Wenn eine Logik erzeugt wird, wird auch gleichermaßen eine JavaFXGUI erstellt und als Parameter an die Logik übergeben.
 *
 * @author Maurice Singh Multani
 */
public class JavaFXGUI implements GUIConnector {

    @Override
    public void gameEnded(boolean gameFinished) {

    }

    /**
     *  Soll bei erfolgreicher Speicherung, dies dem Spieler auch mitteilen.
     */
    public void showSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("The Game was successfully saved!");
        alert.setContentText("You may close the Window.");
        alert.showAndWait();
    }

    /**
     * Soll bei einer nicht erfolgreichen Speicherung, dies dem Spieler auch mitteilen.
     */
    public void showFail() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("The Game was not saved!");
        alert.setContentText("You may close the Window.");
        alert.showAndWait();
    }
}
