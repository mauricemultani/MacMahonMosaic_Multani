package logic;

/**
 * Die Klasse Solvability. Hier wird die Lösbarkeit überprüft.
 *
 * @author Maurice Singh Multani
 */
public class Solvability {

    private final Board board;

    public Solvability(Board board) {
        this.board = board;
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
        if (!allTilesPlaced(board)) {
            return false;
        }

        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
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
                if (board.getCell(row, column).getTile() == MosaicTile.NNNN ||
                        !board.getCell(row, column).isPlaced() &&
                        !board.getCell(row, column).isHole()) {
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
