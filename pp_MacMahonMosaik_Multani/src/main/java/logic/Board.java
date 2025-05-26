package logic;

import javafx.scene.layout.GridPane;

import java.awt.*;

import static logic.MosaicTile.*;

/**
 * Stellt das Spielfeld dar.
 *
 * @author Maurice Singh Multani
 */
public class Board {

    private final int rows;
    private final int columns;
    private final BoardCell[][] cells;
    private final Color[][] borderColors;

    private final MosaicTile[][] board = {
            {NNNN, GGGG, GGGG, GGGG, NNNN}, // x = 0
            {GGGG, NNNN, NNNN, NNNN, GGGG}, // x = 1
            {GGGG, NNNN, NNNN, NNNN, GGGG},
            {GGGG, NNNN, NNNN, NNNN, GGGG},
            {NNNN, RRRR, RRRR, RRRR, NNNN}
    };

    public Board(int rows, int columns, BoardCell[][] cells, Color[][] borderColors, GridPane gridPane) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new BoardCell[rows][columns];
        this.borderColors = borderColors;
    }

    public BoardCell getCell(int row, int column) {
        return cells[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     * Schaut sich die Position einer leeren Zelle an und gibt zurück, ob dort ein Mosaik-Tile reinpasst.
     * @return Ob an der Position das Mosaik-Tile passt oder nicht.
     */
    public boolean isPositionValid(Position pos){
        if (cells[pos.getRow()][pos.getColumn()].isHole()){
            return false;
        }
        return pos.getRow() >= 0 && pos.getRow() < board.length &&
                pos.getColumn() >= 0 && pos.getColumn() < board[0].length;
    }

    public boolean fitsNeighbours(MosaicTile tile, Rotation rotation, Position pos) {
        Color[] tileColors = tile.getColors(rotation);

        int[][] directions = {
                {-1, 0}, // oberer Nachbar
                {1, 0},  // unterer Nachbar
                {0, -1}, // linker Nachbar
                {0, 1}   // rechter Nachbar
        };

        for (int i = 0; i < 4; i++){
            int newRow = pos.getRow() + directions[i][0];
            int newCol = pos.getColumn() + directions[i][1];

            // Überprüfung ob Nachbar
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= columns) {
                BoardCell neighbourCell = cells[newRow][newCol];

                if (neighbourCell.isPlaced()) {
                    Color[] neighbourColors = neighbourCell.getTile().getColors(neighbourCell.getRotation());
                    int oppositeSide = (i + 2) % 4;

                    if (!tileColors[i].equals(neighbourColors[oppositeSide])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Schaut sich an, ob das MosaikTile irgendwo auf dem Spielfeld passt.
     * @param board das Spielfeld
     * @param tile das Mosaik-Tile, welches nach einer freien Position sucht.
     * @return Ob das Mosaik-Tile überhaupt im Spielfeld passt.
     */
    public boolean doesTileFitAnywhere(Board board, MosaicTile tile){
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < columns; col++){
                Position pos = new Position(row, col);

                if (isPositionValid(pos) && !cells[row][col].isPlaced()) {

                    for (Rotation rotation : Rotation.values()) {
                        if (fitsNeighbours(tile, rotation, pos)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Fügt ein Mosaik-Tile an einer Position hinzu.
     * @param mosaicTile das Mosaik-Tile welches an einer Position hinzugefügt werden soll.
     */
    public void placeTileAt(MosaicTile mosaicTile, Rotation rotation, Position pos){
        cells[pos.getRow()][pos.getColumn()].placeTile(mosaicTile, rotation);
    }

}
