package logic;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The class with the tests for the Gamelogic. It should be substituted by well-named
 * Testclasses for the written classes in the logic-package.
 */

public class GameTest implements Solvability{

    @Test
    void doesTileFitAtAnywhere_allNeighboursPlaced() {
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGGG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGGG.png")));
        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGGG.png")));
        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR.png")));

        gameField.placeTileAt(topLeftTile, 0, 0);
        gameField.placeTileAt(topMiddleTile, 0, 1);
        gameField.placeTileAt(topRightTile, 0, 2);
        gameField.placeTileAt(middleLeftTile, 1, 0);
        gameField.placeTileAt(middleRightTile, 1, 2);
        gameField.placeTileAt(bottomLeftTile, 2, 0);
        gameField.placeTileAt(bottomMiddleTile, 2, 1);
        gameField.placeTileAt(bottomRightTile, 2, 2);

        boolean result = gameField.doesTileFitAnywhere(gameField, testingTile);

        assertFalse(result);
    }

    @Test
    void doesTileFitAtAnywhere_placedOnEdge_allNeighboursPlaced() {
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGGG.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));
        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGGG.png")));
        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGGG.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR.png")));

        gameField.placeTileAt(topLeftTile, 0, 0);
        gameField.placeTileAt(topRightTile, 0, 2);
        gameField.placeTileAt(middleLeftTile, 1, 0);
        gameField.placeTileAt(middleMiddleTile, 1, 1);
        gameField.placeTileAt(middleRightTile, 1, 2);
        gameField.placeTileAt(bottomLeftTile, 2, 0);
        gameField.placeTileAt(bottomMiddleTile, 2, 1);
        gameField.placeTileAt(bottomRightTile, 2, 2);

        boolean result = gameField.doesTileFitAnywhere(gameField, testingTile);

        assertFalse(result);

    }

    @Test
    void doesTileFitAtAnywhere_placedOnCorner_noTilesYetPlaced() {
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        boolean result = gameField.doesTileFitAnywhere(gameField, testingTile);

        assertTrue(result);
    }

    @Test
    void doesTileFitAnywhere_borderingOnHole() {
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile HoleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));

        boolean result = gameField.doesTileFitAnywhere(gameField, testingTile);

        assertTrue(result);
    }

    @Test
    void doesTileFitAt_allNeighboursPlaced() {
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile topTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGGG.png")));
        MosaicTile bottomTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile leftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
        MosaicTile rightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR.png")));


        gameField.placeTileAt(topTile, 1, 2);
        gameField.placeTileAt(bottomTile, 3, 2);
        gameField.placeTileAt(leftTile, 2, 3);
        gameField.placeTileAt(rightTile, 2, 1);

        boolean result = gameField.doesTileFitAt(testingTile, 2, 2);

        assertFalse(result);
    }

    @Test
    void doesTileFitAt_placedOnEdge_allNeighboursPlaced() {
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile bottomTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile leftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
        MosaicTile rightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR.png")));

        gameField.placeTileAt(bottomTile, 1, 2);
        gameField.placeTileAt(leftTile, 0, 1);
        gameField.placeTileAt(rightTile, 0, 3);

        boolean result = gameField.doesTileFitAt(testingTile, 0, 2);

        assertFalse(result);
    }

    @Test
    void doesTileFitAt_placedOnCorner_noTilesYetPlaced(){
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile bottomTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY.png")));
        MosaicTile leftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));

        gameField.placeTileAt(bottomTile, 1, 4);
        gameField.placeTileAt(leftTile, 0, 3);

        boolean result = gameField.doesTileFitAt(testingTile, 0, 4);

        assertFalse(result);
    }

    @Test
    void doesTileFitAt_borderingOnHole(){
        GameField gameField = new GameField();
        MosaicTile testingTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));

        MosaicTile HoleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));

        gameField.placeTileAt(HoleTile, 0, 2);

        boolean result = gameField.doesTileFitAt(testingTile, 0, 3);

        assertFalse(result);
    }
}
