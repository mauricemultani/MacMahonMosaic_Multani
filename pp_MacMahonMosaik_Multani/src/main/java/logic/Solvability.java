package logic;

import logic.utils.BoardCell;
import logic.utils.MosaicTile;
import logic.utils.Position;
import logic.utils.Rotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Die Klasse Solvability. Hier wird die Lösbarkeit überprüft.
 *
 * @author Maurice Singh Multani
 */
public class Solvability {

    /**
     * Ein Set aus den platzierbaren Mosaikteilen in der Class Mosaictile.
     */
    private static final Set<MosaicTile> USABLE_TILES = Set.of(
            MosaicTile.GGGG, MosaicTile.GGRR, MosaicTile.GRGR, MosaicTile.GRRR,
            MosaicTile.GRYG, MosaicTile.GRYR, MosaicTile.GYRG, MosaicTile.GYYY,

            MosaicTile.RGGG, MosaicTile.RGYG, MosaicTile.RGYR, MosaicTile.RRRR,
            MosaicTile.RRYY, MosaicTile.RYGR, MosaicTile.RYGY, MosaicTile.RYYY,

            MosaicTile.YGGG, MosaicTile.YGRY, MosaicTile.YGYG, MosaicTile.YRGY,
            MosaicTile.YRRR, MosaicTile.YRYR, MosaicTile.YYGG, MosaicTile.YYYY

    );

    /**
     * Prüft, ob das Spiel lösbar ist.
     *
     * Spiel ist erfolgreich lösbar wenn:
     * 1. Alle Teile, die sich an einer Kante berühren dieselbe Farbe haben.
     * 2. Alle möglich belegbaren Zellen belegt sind.
     *
     * Spiel ist nicht lösbar, wenn ein Teil falsch platziert ist.
     */
    public boolean solveGame(Board board){
        if (!allCellsPlaced(board)) {
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
    public boolean possibleSolvation(Board board){
        List<MosaicTile> usableTiles = new ArrayList<>(USABLE_TILES);

        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                Position pos = new Position(row, col);
                BoardCell cell = board.getCell(row, col);

                if (cell.isPlaced() && !cell.isHole() && cell.getTile() != null) {
                    usableTiles.remove(cell.getTile());
                }

                if (!cell.isPlaced() && !cell.isHole()) {
                    for (MosaicTile tile : new ArrayList<>(usableTiles)) {
                        for (Rotation rotation : Rotation.values()) {
                            if (board.fitsNeighbours(tile, rotation, pos)
                                    && board.fitsBorderNeighbours(tile, rotation, pos)) {

                                board.placeTileAt(tile, rotation, pos);
                                usableTiles.remove(tile);

                                if (possibleSolvation(board)) {
                                    return solveGame(board);
                                } else {
                                    board.removeTileAt(pos);
                                    usableTiles.add(tile);
                                }
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return solveGame(board);
    }

    /**
     * Hilfsmethode um die Lösung zu prüfen.
     * Prüft, ob alle Zellen belegt sind.
     *
     * true, wenn alle Zellen belegt sind, ansonsten false
     */
    public boolean allCellsPlaced(Board board) {
        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                BoardCell cell = board.getCell(row, col);

                if (!cell.isPlaced() && !cell.isHole()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Methode, welche überprüft wie viele leere Zellen noch im Spielfeld genau sind.
     * Wenn es über 18 leere Zellen sind, soll es true angeben, ansonsten false
     */
    public boolean overEighteenEmptyCells(Board board) {
        int emptyCells = 0;
        final int MAXIMUM_AMOUNT_OF_EMPTY_CELLS = 18;

        for (int row = 1; row < board.getRows() - 1; row++) {
            for (int col = 1; col < board.getColumns() - 1; col++) {
                BoardCell cell = board.getCell(row, col);

                if (!cell.isHole() && !cell.isPlaced()) {
                    emptyCells++;
                }
            }
        }

        return emptyCells > MAXIMUM_AMOUNT_OF_EMPTY_CELLS;
    }
}
