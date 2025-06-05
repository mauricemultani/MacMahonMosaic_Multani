package logic;

/**
 * Die Verbindung von Logik zur GUI.
 *
 * @author Maurice Singh Multani
 */
public interface GUIConnector {

    /**
     * Wird angezeigt, wenn das Spiel korrekt beendet ist.
     * @param gameFinished boolescher Wert um zu bestimmen, ob das Spiel zu Ende ist.
     */
    void gameEnded(boolean gameFinished);



}
