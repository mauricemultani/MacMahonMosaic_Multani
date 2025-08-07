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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

        board.getCell(1, 3).setRotation(Rotation.DEGREE_90);  // GYRG
        board.getCell(2, 1).setRotation(Rotation.DEGREE_270); // RYGR
        board.getCell(2, 3).setRotation(Rotation.DEGREE_180); // RGYR
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180); // YGRY
        board.getCell(3, 3).setRotation(Rotation.DEGREE_270); // YRGY

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

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
        Solvability solve = new Solvability(board, null, null);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_180); // RYYY
        board.getCell(1, 2).setRotation(Rotation.DEGREE_270); // YGGG

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
        Solvability solve = new Solvability(board, null, null);

        board.getCell(1, 1).setRotation(Rotation.DEGREE_90);  // GGRR
        board.getCell(3, 1).setRotation(Rotation.DEGREE_180); // YRGY
        board.getCell(1, 2).setRotation(Rotation.DEGREE_270); // GYRG
        board.getCell(3,2).setRotation(Rotation.DEGREE_90);   // GRYG
        board.getCell(2, 2).setRotation(Rotation.DEGREE_270); // RGYG


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
        Solvability solve = new Solvability(board, null, null);

        boolean result = solve.solveGame();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_2X2_field() {
        String[][] field = {
                {"NNNN", "NNYN", "NNGN", "NNNN"},
                {"NYNN", "NNNN", "NNNN", "NNNG"},
                {"NYNN", "NNNN", "NNNN", "NNNR"},
                {"NNNN", "YNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_3X3_field() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNNN"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNY"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_4X4_field() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNRN", "NNNN"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNY"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "GNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void overEighteenEmptyCells_5x5_field() {
        String[][] field = {
                {"NNNN", "NNRN", "NNYN", "NNGN", "NNRN", "NNRN", "NNNN"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNR"},
                {"NRNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNY"},
                {"NGNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NYNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "GNNN", "RNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.overEighteenEmptyCells();
        // über 18 Zellen = Lösbarkeitsprüfung wird geskippt.

        assertTrue(result);
    }

    @Test
    void possibleSolvation_exampleFieldEmpty() {
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
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_exampleFieldEmpty_withHole() {
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
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_exampleFieldNearlySolved() {
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
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_exampleFieldNearlySolved_withHole() {
        String[][] field = {
                {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"},
                {"NGNN", "GRYG", "GRYR", "GGYR", "NNNG"},
                {"NRNN", "YGRR", "HHHH", "YRRG", "NNNR"},
                {"NGNN", "RYYG", "RYGY", "RGYY", "NNNG"},
                {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertTrue(result);
    }

    @Test
    void possibleSolvation_4X4_field_wrongTilePlaced() {
        String[][] field = {
                {"NNNN", "NNGN", "NNRN", "NNYN", "NNYN", "NNNN"},
                {"NGNN", "GYRG", "RGYR", "NNNN", "NNNN", "NNNG"},
                {"NYNN", "RYYY", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NYNN", "GGGG", "NNNN", "NNNN", "NNNN", "NNNY"}, // GGGG ist falsch platziert
                {"NGNN", "RYGY", "RRYY", "NNNN", "GGRR", "NNNG"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "RNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        board.getCell(1, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(4, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(4, 2).setRotation(Rotation.DEGREE_180);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertFalse(result);
    }

    @Test
    void possibleSolvation_4X4_field_wrongTilePlaced_withHole() {
        String[][] field = {
                {"NNNN", "NNGN", "NNRN", "NNYN", "NNYN", "NNNN"},
                {"NGNN", "GYRG", "RGYR", "NNNN", "NNNN", "NNNG"},
                {"NYNN", "RYYY", "NNNN", "NNNN", "NNNN", "NNNG"},
                {"NYNN", "GGGG", "HHHH", "NNNN", "NNNN", "NNNY"}, // GGGG ist falsch platziert.
                {"NGNN", "RYGY", "RRYY", "NNNN", "GGRR", "NNNG"},
                {"NNNN", "YNNN", "RNNN", "GNNN", "RNNN", "NNNN"}
        };

        int rows = field.length;
        int cols = field[0].length;

        Board board = new Board(rows, cols, field, false);
        Editor editor = new Editor(board);
        BoardOptions options = new BoardOptions(board);

        board.getCell(1, 2).setRotation(Rotation.DEGREE_90);
        board.getCell(4, 1).setRotation(Rotation.DEGREE_90);
        board.getCell(4, 2).setRotation(Rotation.DEGREE_180);

        Solvability solve = new Solvability(board, editor, options);

        boolean result = solve.possibleSolvation();

        assertFalse(result);
    }
}
