package logic;

import javafx.scene.control.ButtonType;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Wird zum Testen verwendet.
 *
 * @author Maurice Singh Multani
 */
public class FakeGUI implements GUIConnector{

    @Override
    public void showGameWon() {

    }

    @Override
    public void showGamesNotFinished() {

    }

    @Override
    public void showSuccessSave() {

    }

    @Override
    public void showFailSave() {

    }

    @Override
    public void showSuccessLoad() {

    }

    @Override
    public void showFailLoad() {

    }

    @Override
    public Optional<ButtonType> showSignWhenHandleClose() {
        return Optional.empty();
    }

    @Override
    public Optional<Pair<Integer, Integer>> whenChangeSizeOfGameField() {
        return Optional.empty();
    }

    @Override
    public void showMissingNumbersForField() {

    }

    @Override
    public void showFieldTooSmall() {

    }

    @Override
    public void showFieldTooBig() {

    }

    @Override
    public void showEditorNotActive() {

    }

    @Override
    public void showHolesToBePlaced(int numHoles) {

    }

    @Override
    public void showAllHolesPlaced() {

    }

    @Override
    public void showOnlyOneHoleToPlace() {

    }

    @Override
    public void showPlaceAllTilesFirst() {

    }

    @Override
    public void showHolesNotPlaced() {

    }

    @Override
    public void showPlacingHoleNotAllowed() {

    }
}
