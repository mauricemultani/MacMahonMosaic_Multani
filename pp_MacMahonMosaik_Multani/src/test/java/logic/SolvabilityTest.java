package logic;

import logic.utils.Rotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolvabilityTest {

    @Test
    void solveGame_exampleEmptyField() {
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertFalse(result);
    }

    @Test
    void solveGame_exampleEmptyField_withOneHole() {
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NRNN", "NNNN", "HHHH", "NNNN", "NNNR"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertFalse(result);
    }

    @Test
    void solveGame_exampleFieldNearlySolved(){
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "GRYG", "GRYR", "GGYR", "NNNG"},
                {"NRNN", "YGRR", "NNNN", "YRRG", "NNNR"},
                {"NGNN", "RYYG", "RYGY", "RGYY", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertFalse(result);
    }

    @Test
    void solveGame_exampleFieldNearlySolved_withOneHole_rotated(){
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "GRYG", "GRYR", "GYRG", "NNNG"},
                {"NRNN", "RYGR", "HHHH", "RGYR", "NNNR"},
                {"NGNN", "YGRY", "RYGY", "YRGY", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 1).setRotation(Rotation.DEGREE_270);
        board.getCell(2, 3).setRotation(Rotation.DEGREE_180);
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(3, 3).setRotation(Rotation.DEGREE_270);

        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void solveGame_fieldWithWrongTilePlaced(){
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "YGRY", "GGGG", "NNNG"},
                {"NYNN", "GGRR", "GRGR", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertFalse(result);
    }

    @Test
    void solveGame_fieldWithWrongTilePlaced_withOneHole(){
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "YGRY", "HHHH", "NNNG"},
                {"NYNN", "GGRR", "GRGR", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertFalse(result);
    }

    @Test
    void solveGame_correctField_2x2_NoRotation() {
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "YGRY", "GGGG", "NNNG"},
                {"NYNN", "RRYY", "GRGR", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void solveGame_correctField_2x2_NoRotation_OneHole() {
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "YGRY", "HHHH", "NNNG"},
                {"NYNN", "RRYY", "GRGR", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void solveGame_correctField_2x2_OnlyHoles() {
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "HHHH", "HHHH", "NNNG"},
                {"NYNN", "HHHH", "HHHH", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void solveGame_correctField_2x2_WithRotation() {
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "RYYY", "YGGG", "NNNG"},
                {"NYNN", "RRYY", "GRGR", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(1, 2).setRotation(Rotation.DEGREE_270);

        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void solveGame_correctField_3x3_WithRotation() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NRNN", "GGRR", "GYRG", "GRRR", "NNNR"},
                {"NRNN", "GRGR", "RGYG", "RRYY", "NNNR"},
                {"NRNN", "YRGY", "GRYG", "YYGG", "NNNY"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(1, 2).setRotation(Rotation.DEGREE_270);
        board.getCell(3,2).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 2).setRotation(Rotation.DEGREE_270);


        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void solveGame_correctField_3x3_OnlyHoles() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NRNN", "HHHH", "HHHH", "HHHH", "NNNR"},
                {"NRNN", "HHHH", "HHHH", "HHHH", "NNNR"},
                {"NRNN", "HHHH", "HHHH", "HHHH", "NNNY"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Solvability solve = new Solvability(board);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180);
        board.getCell(1, 2).setRotation(Rotation.DEGREE_270);
        board.getCell(3,2).setRotation(Rotation.DEGREE_90);
        board.getCell(2, 2).setRotation(Rotation.DEGREE_270);


        boolean result = solve.solveGame();

        assertTrue(result);
    }
}
