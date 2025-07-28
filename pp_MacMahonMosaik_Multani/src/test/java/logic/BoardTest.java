package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @Test
    void fitsNeighbours_allNeighbouringTilesPlaced() {
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "GRYG", "GRYR", "GYRG", "NNNG"},
                {"NRNN", "RYGR", "NNNN", "RGYR", "NNNR"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NGNN", "YGRY", "RYGY", "YRGY", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 1).setRotation(Rotation.DEGREE_270);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_180);
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(3, 3).setRotation(Rotation.DEGREE_270);

        boolean result = board.fitsNeighbours(MosaicTile.RGYG, Rotation.DEGREE_180, new Position(2, 2));

        assertTrue(result);
    }

    @Test
    void fitsNeighbours_allNeighbouringTilesPlaced_withHole() {
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "GRYG", "GRYR", "GYRG", "NNNG"},
                {"NRNN", "HHHH", "NNNN", "RGYR", "NNNR"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NGNN", "YGRY", "RYGY", "YRGY", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 1).setRotation(Rotation.DEGREE_270);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_180);
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(3, 3).setRotation(Rotation.DEGREE_270);

        boolean result = board.fitsNeighbours(MosaicTile.RGYG, Rotation.DEGREE_180, new Position(2, 2));

        assertTrue(result);
    }

    @Test
    void fitsNeighbours_allNeighbouringTilesPlaced_placingTileAtCorner() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NGNN", "RYGR", "GRYR", "GYRG", "NNNG"},
                {"NRNN", "GRYG", "YGGG", "RYYY", "NNNR"},
                {"NGNN", "NNNN", "YGYG", "GYYY", "NNNG"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(1, 2).setRotation(Rotation.DEGREE_180);
        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(2, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(2, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(3, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(3, 3).setRotation(Rotation.DEGREE_90);

        boolean result = board.fitsNeighbours(MosaicTile.YYGG, Rotation.DEGREE_90, new Position(3, 1));

        assertTrue(result);
    }

    @Test
    void fitsNeighbours_allNeighbouringTilesPlaced_placingTileAtCorner_withHole() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NGNN", "RYGR", "GRYR", "GYRG", "NNNG"},
                {"NRNN", "GRYG", "YGGG", "RYYY", "NNNR"},
                {"NGNN", "NNNN", "HHHH", "GYYY", "NNNG"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(1, 2).setRotation(Rotation.DEGREE_180);
        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(2, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(2, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(3, 3).setRotation(Rotation.DEGREE_90);

        boolean result = board.fitsNeighbours(MosaicTile.YYGG, Rotation.DEGREE_90, new Position(3, 1));

        assertTrue(result);
    }

    @Test
    void fitsNeighbours_allNeighbouringTilesPlaced_placingWrongTileAtCorner() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NGNN", "RYGR", "GRYR", "GYRG", "NNNG"},
                {"NRNN", "GRYG", "YGGG", "RYYY", "NNNR"},
                {"NGNN", "NNNN", "YGYG", "GYYY", "NNNG"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(1, 2).setRotation(Rotation.DEGREE_180);
        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(2, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(2, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(3, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(3, 3).setRotation(Rotation.DEGREE_90);

        boolean result = board.fitsNeighbours(MosaicTile.GGRR, Rotation.DEGREE_0, new Position(3, 1));

        assertFalse(result);
    }

    @Test
    void fitsNeighbours_allNeighbouringTilesPlaced_placingWrongTileAtCorner_withHole() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NGNN", "RYGR", "HHHH", "NNNN", "NNNG"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NRNN", "GRYG", "YGGG", "RYYY", "NNNR"},
                {"NGNN", "YYGG", "YGYG", "GYYY", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);

        board.getCell(2, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(2, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_90);

        board.getCell(3, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(3, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(3, 3).setRotation(Rotation.DEGREE_90);

        boolean result = board.fitsNeighbours(MosaicTile.GGRR, Rotation.DEGREE_0, new Position(1, 3));

        assertFalse(result);
    }

    @Test
    void fitsNeighbours_atCorner_emptyField() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);

        boolean result = board.fitsNeighbours(MosaicTile.GRYG, Rotation.DEGREE_0, new Position(1, 1));

        assertFalse(result);
    }

//    @Test
//    void doesTileFitAnywhere_atCorner_emptyField() {
//        String[][] field = {
//                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
//                {"NGNN", "RGYG", "NNNN", "NNNN", "NNNG"}, // in dieser Zeile bei NNNN wird das Mosaikteil platziert
//                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNR"},
//                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
//                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
//        };
//
//        int rows = field.length;
//        int cols = field[0].length;
//
//        Board board = new Board(rows, cols, field, false);
//
//        boolean result = board.doesTileFitAnywhere(MosaicTile.GRGR);
//
//        assertTrue(result);
//    }
}
