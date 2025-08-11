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
    public Optional<Pair<Integer, Integer>> whenChangingSizeOfGameField() {
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
    public void showHolesNotPlaced_Editor() {

    }

    @Override
    public void showHolesNotPlaced_Save() {

    }

    @Override
    public void showPlacingHoleNotAllowed() {

    }

    @Override
    public void showNoPlaceableTileFound() {

    }

    @Override
    public void showPossibleSolvation() {

    }

    @Override
    public void showNoPossibleSolvation() {

    }

    @Override
    public void showSkipSolvabilityCheck() {

    }

    @Override
    public void showSkipHelp() {

    }

    @Override
    public void showNotAvailableInEditor() {

    }
}
