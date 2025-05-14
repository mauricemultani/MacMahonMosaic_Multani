package logic;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolvabilityTest implements Solvability{

    @Test
    void solveGame_emptyField() {
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleMiddleTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = solveGame(gameField);

        assertFalse(result);
    }

    @Test
    void solveGame_exampleFieldNearlySolved(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = solveGame(gameField);

        assertFalse(result);
    }

    @Test
    public void solveGame_oneTilePlacedFalsely(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = solveGame(gameField);

        assertFalse(result);
    }

    @Test
    public void solveGame_emptyField_withHole(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleMiddleTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = solveGame(gameField);

        assertFalse(result);
    }

    @Test
    public void solveGame_exampleFieldNearlySolved_withHole(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = solveGame(gameField);

        assertFalse(result);
    }

    @Test
    public void solveGame_oneTilePlacedFalsely_withHole(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = solveGame(gameField);

        assertFalse(result);
    }

    @Test
    public void finishGame_doneField(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYG.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = gameDone(gameField);

        assertTrue(result);
    }

    @Test
    public void finishGame_exampleFieldNearlySolved(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = gameDone(gameField);

        assertFalse(result);
    }

    @Test
    public void finishGame_partiallyDoneField_OneTilePlacedWrong(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = gameDone(gameField);

        assertFalse(result);
    }

    @Test
    public void finishGame_doneField_withHole(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = gameDone(gameField);

        assertFalse(result);
    }

    @Test
    public void finishGame_exampleFieldNearlySolved_withHole(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GYRG.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/NNNN.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = gameDone(gameField);

        assertFalse(result);
    }

    @Test
    public void finishGame_partiallyDoneField_OneTilePlacedWrong_withHole(){
        GameField gameField = new GameField();

        MosaicTile borderTopLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderTopRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile topLeftTile      = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYG.png")));
        MosaicTile topMiddleTile    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GRYR.png")));
        MosaicTile topRightTile     = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/HHHH.png")));

        MosaicTile borderLeftTop    = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderLeftMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderLeftBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        MosaicTile bottomLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YGRY.png")));
        MosaicTile bottomMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGY.png")));
        MosaicTile bottomRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YRGY.png")));

        MosaicTile borderBottomLeft = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));
        MosaicTile borderBottomMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderBottomRight = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/YYYY_ohne_Linien.png")));

        MosaicTile middleLeftTile   = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RYGR.png")));
        MosaicTile middleMiddleTile = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR.png")));
        MosaicTile middleRightTile  = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RGYR.png")));

        MosaicTile borderRightTop = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));
        MosaicTile borderRightMiddle = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/RRRR_ohne_Linien.png")));
        MosaicTile borderRightBottom = new MosaicTile(new Image(getClass().getResourceAsStream("/gui/tiles/GGGG_ohne_Linien.png")));

        gameField.placeTileAt(borderTopLeft, 0, 1);
        gameField.placeTileAt(borderTopMiddle, 0, 2);
        gameField.placeTileAt(borderTopRight, 0 , 3);

        gameField.placeTileAt(borderLeftTop, 1, 0);
        gameField.placeTileAt(borderLeftMiddle, 2, 0);
        gameField.placeTileAt(borderLeftBottom, 3, 0);

        gameField.placeTileAt(topLeftTile, 1, 1);
        gameField.placeTileAt(topMiddleTile, 1, 2);
        gameField.placeTileAt(topRightTile, 1, 3);

        gameField.placeTileAt(middleLeftTile, 2, 1);
        gameField.placeTileAt(middleMiddleTile, 2, 2);
        gameField.placeTileAt(middleRightTile, 2, 3);

        gameField.placeTileAt(borderRightTop, 1, 4);
        gameField.placeTileAt(borderRightMiddle, 2, 4);
        gameField.placeTileAt(borderRightBottom, 3, 4);

        gameField.placeTileAt(bottomLeftTile, 3, 1);
        gameField.placeTileAt(bottomMiddleTile, 3, 2);
        gameField.placeTileAt(bottomRightTile, 3, 3);

        gameField.placeTileAt(borderBottomLeft, 4, 1);
        gameField.placeTileAt(borderBottomMiddle, 4, 2);
        gameField.placeTileAt(borderBottomRight, 4, 3);

        boolean result = gameDone(gameField);

        assertFalse(result);
    }
}
