package logic;

import javafx.scene.control.ButtonType;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Die Verbindung von Logik zur GUI.
 *
 * @author Maurice Singh Multani
 */
public interface GUIConnector {

    /**
     * Mitteilung, welche eine Willkommensnachricht sein soll.
     */
    void showWelcomeToGame();

    /**
     * Wird angezeigt, wenn das Spiel korrekt beendet ist.
     */
    void showGameWon();

    /**
     * Wird angezeigt, wenn die Lösung des Spiels nicht korrekt ist.
     */
    void showGamesNotFinished();

    /**
     * Soll bei erfolgreicher Speicherung, dies dem Spieler auch mitteilen.
     */
    void showSuccessSave();

    /**
     * Soll bei einer nicht erfolgreichen Speicherung, dies dem Spieler auch mitteilen.
     */
    void showFailSave();

    /**
     * Mitteilung beim erfolgreichen Laden eines gespeicherten Spielstands.
     */
    void showSuccessLoad();

    /**
     * Mitteilung beim fehlerhaften Laden eines gespeicherten Spielstands.
     */
    void showFailLoad();

    /**
     * Mitteilung, wenn der Spieler das Spiel schließen will.
     */
    Optional<ButtonType> showSignWhenHandleClose();

    /**
     * Mitteilung, wenn der Spieler im Editor-Modus sein Board verändern will.
     */
    Optional<Pair<Integer, Integer>> whenChangeSizeOfGameField();

    /**
     * Mitteilung, wenn eines der Felder für die Spielfeldgröße leer ist.
     */
    void showMissingNumbersForField();

    /**
     * Mitteilung, wenn das Spielfeld zu klein ist.
     */
    void showFieldTooSmall();

    /**
     * Mitteilung, wenn das Spielfeld zu groß ist.
     */
    void showFieldTooBig();

    /**
     * Mitteilung, wenn der Editor nicht aktiviert ist.
     */
    void showEditorNotActive();

    /**
     * Mitteilung, wenn Löcher zu platzieren sind.
     */
    void showHolesToBePlaced(int numHoles);

    /**
     * Mitteilung, wenn alle Löcher platziert sind.
     */
    void showAllHolesPlaced();

    /**
     * Mitteilung, wenn nur ein Loch zu platzieren ist.
     */
    void showOnlyOneHoleToPlace();

    /**
     * Mitteilung, wenn nicht alle freie Zellen belegt sind.
     */
    void showPlaceAllTilesFirst();

    /**
     * Mitteilung, wenn Editormodus frühzeitig versucht wird zu verlassen,
     * ohne das Löcher gesetzt wurden.
     */
    void showHolesNotPlaced();

    /**
     * Mitteilung, wenn versucht wird ein Loch auf ein Loch im Editormodus zu platzieren.
     */
    void showPlacingHoleNotAllowed();

    /**
     * Mitteilung, wenn kein platzierbares Mosaikteil gefunden wurde.
     */
    void showNoPlaceableTileFound();

    /**
     * Mitteilung, welche kommen soll, wenn beim Verlassen des Editormodus in den Spielmodus,
     * vom Programm festgestellt wird, dass mit dem dargestellten Spielfeld keine mögliche Lösung möglich ist.
     */
    void showNoPossibleSolvation();
}
