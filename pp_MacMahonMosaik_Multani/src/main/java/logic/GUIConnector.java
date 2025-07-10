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
     * Wird angezeigt, wenn das Spiel korrekt beendet ist.
     * @param gameFinished boolescher Wert um zu bestimmen, ob das Spiel zu Ende ist.
     */
    void gameEnded(boolean gameFinished);


    /**
     * Soll bei erfolgreicher Speicherung, dies dem Spieler auch mitteilen.
     */
    void showSuccessSave();

    void showFailSave();

    void showSuccessLoad();

    void showFailLoad();

    Optional<ButtonType> showSignWhenHandleClose();

    Optional<Pair<Integer, Integer>> whenChangeSizeOfGameField();

    void showFieldTooSmall();

    void showFieldTooBig();

    void showEditorNotActive();

    void showHolesToBePlaced(int numHoles);

    void showAllHolesPlaced();

    void showOnlyOneHoleToPlace();
}
