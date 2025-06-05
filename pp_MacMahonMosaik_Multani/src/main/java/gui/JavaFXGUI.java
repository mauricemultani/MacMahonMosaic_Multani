package gui;

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
}
