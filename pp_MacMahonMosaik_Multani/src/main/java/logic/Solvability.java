package logic;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

/**
 * Die Klasse Solvability. Hier wird die Lösbarkeit überprüft.
 *
 * @author Maurice Singh Multani
 */
public class Solvability {

    private final int rows;
    private final int columns;

    private final MosaicTile tile;

    private final Rotation rotation;

    private final BoardCell cell;

    @FXML
    GridPane gameField;

    public Solvability(int rows, int columns, MosaicTile tile, Rotation rotation, BoardCell cell) {
        this.rows = rows;
        this.columns = columns;
        this.tile = tile;
        this.rotation = rotation;
        this.cell = cell;
    }

    /**
     * Prüft, ob das Spiel lösbar ist.
     *
     * Spiel ist erfolgreich lösbar wenn:
     * 1. Zwei Teile, die sich an einer Kante berühren dieselbe Farbe haben.
     * 2. Alle möglich belegbaren Zellen belegt sind.
     *
     * Spiel ist nicht lösbar, wenn ein Teil falsch platziert ist.
     * Spieler wird auch über die Lösbarkeit im aktuellen Spielstand informiert.
     */
    public boolean solveGame(Board board, Position pos){
        return board.isPositionValid(pos)
                && board.fitsNeighbours(tile, rotation, pos)
                && allTilesPlaced(board);
    }

    /**
     * Prüft im aktuellen Spielstand, bei freien Stellen und verfügbaren Mosaikteilen, ob es eine mögliche Lösung gibt.
     */
    public void possibleSolvation(){

    }

    /**
     * Soll dem Spieler die Möglichkeit geben, einen Tipp für die Lösung zu bekommen.
     * Prüft zunächst auf eine mögliche Lösung.
     *
     * Wenn es eine mögliche Lösung gibt, wird eine leere Zelle belegt.
     *  - Die leere Zelle muss an Rand oder an min. einer belegten Zelle angrenzen.
     *
     *  Unendliche Nutzung möglich. Puzzle kann auch nur mit der Hilfe gelöst werden.
     */
    public void helpSolve() {

    }

    /**
     * Hilfsmethode um die Lösung zu prüfen.
     * Prüft, ob die Kanten dieselben Farben haben.
     */
    public void sameColouredEdges() {

    }

    /**
     * Hilfsmethode um die Lösung zu prüfen.
     * Prüft, ob alle Zellen belegt sind.
     */
    public boolean allTilesPlaced(Board board) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int column = 0; column < board.getColumns(); column++) {
                if (!board.getCell(row, column).isPlaced() && !board.getCell(row, column).isHole()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prüfung zur Testung, ob das Spiel fertig ist.
     */
    public boolean gameDone(Board gameField){
        return true;
    }
}
