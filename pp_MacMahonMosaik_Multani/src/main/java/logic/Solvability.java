package logic;

/**
 * Die Klasse Solvability. Hier wird die Lösbarkeit überprüft.
 *
 * @author Maurice Singh Multani
 */
public class Solvability {

    private final MosaicTile tile;

    private final Rotation rotation;

    private final Board board;

    private final Position pos;

    public Solvability(MosaicTile tile, Rotation rotation, Board board, Position pos) {
        this.tile = tile;
        this.rotation = rotation;
        this.board = board;
        this.pos = pos;
    }

    /**
     * Prüft, ob das Spiel lösbar ist.
     *
     * Spiel ist erfolgreich lösbar wenn:
     * 1. Alle Teile, die sich an einer Kante berühren dieselbe Farbe haben.
     * 2. Alle möglich belegbaren Zellen belegt sind.
     *
     * Spiel ist nicht lösbar, wenn ein Teil falsch platziert ist.
     */
    public boolean solveGame(){
        // if-Überprüfung, ob alle Zellen belegt sind
        if (!allTilesPlaced(board)) {
            return false;
        }

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                // Spielzelle mit der Position von Reihe und Spalte
                BoardCell cell = board.getCell(row, col);

                // Farben der Nachbarn bei einer
                // belegten Zelle überprüfen
                if (cell.isPlaced()) {
                    Position pos = new Position(row, col);
                    if (!board.fitsNeighbours(cell.getTile(), cell.getRotation(), pos)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Prüft im aktuellen Spielstand, bei freien Stellen und
     * verfügbaren Mosaikteilen, ob es eine mögliche Lösung gibt.
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
        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int column = 1; column < board.getColumns() - 1; column++) {
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
    public boolean gameDone() {
        return solveGame();
    }
}
