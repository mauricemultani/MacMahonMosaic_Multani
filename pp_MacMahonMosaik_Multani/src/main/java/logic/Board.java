package logic;

import java.awt.*;
import java.util.Random;

/**
 * Stellt das Spielfeld dar, auf dem die Mosaikteile platziert werden können.
 * Klasse kümmert sich um Validierung von Positionen, den Nachbarn,
 * platzieren von Tiles und allgemein auch die Verwaltung der Zellen.
 *
 * @author Maurice Singh Multani
 */
public class Board {

    private final int rows;
    private final int columns;
    private final BoardCell[][] cells;
    private MosaicTile tile;

    private final String[] topBorderColors;
    private final String[] bottomBorderColors;
    private final String[] leftBorderColors;
    private final String[] rightBorderColors;

    Random random = new Random();

    /**
     * Konstruktor für Spielfeld. Setzt die Anzahl an Reihen und Spalten.
     * Initialisiert das Spielfeld mit leeren Zellen und auch mit Löchern (falls Zellen > 24).
     * Initialisiert ebenfalls auch die Randfarben.
     *
     * @param rows    die Anzahl an Reihen im Spielfeld.
     * @param columns die Anzahl an Spalten im Spielfeld.
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new BoardCell[rows][columns];

        boolean[][] holes = generateHoles(getRows(), getColumns());
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (holes[row][col]) {
                    this.cells[row][col] = new BoardCell(true);
                } else {
                    this.cells[row][col] = new BoardCell(null);
                }

            }
        }

        this.topBorderColors = initRandomColors(columns, topBorderImages, random);
        this.bottomBorderColors = initRandomColors(columns, bottomBorderImages, random);
        this.leftBorderColors = initRandomColors(rows, leftBorderImages, random);
        this.rightBorderColors = initRandomColors(rows, rightBorderImages, random);
    }

    public Board(int rows, int columns, BoardCell[][] saveCells) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new BoardCell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                BoardCell savedCell = saveCells[row][column];
                this.cells[row][column] = new BoardCell(
                        savedCell.getTile()
                );
            }
        }

        this.topBorderColors = initRandomColors(columns, topBorderImages, random);
        this.bottomBorderColors = initRandomColors(columns, bottomBorderImages, random);
        this.leftBorderColors = initRandomColors(rows, leftBorderImages, random);
        this.rightBorderColors = initRandomColors(rows, rightBorderImages, random);
    }

    /**
     * Gibt die Zelle an der angegebenen Position wieder zurück.
     *
     * @param row    Reihe der Zelle
     * @param column Spalte der Zelle
     * @return Zelle an der angegebenen Position
     */
    public BoardCell getCell(int row, int column) {
        return cells[row][column];
    }

    /**
     * Gibt die Anzahl der Reihen zurück.
     *
     * @return Anzahl an Reihen.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gibt die Anzahl an Spalten zurück.
     *
     * @return Anzahl an Spalten
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gibt Farben des oberen Spielfeldrands zurück.
     *
     * @return Array mit Farbwerten vom oberen Spielfeldrand.
     */
    public String[] getTopBorderColors() {
        return topBorderColors;
    }

    /**
     * Gibt Farben des unteren Spielfeldrands zurück.
     *
     * @return Array mit Farbwerten vom unteren Spielfeldrand.
     */
    public String[] getBottomBorderColors() {
        return bottomBorderColors;
    }

    /**
     * Gibt Farben des linken Spielfeldrands zurück.
     *
     * @return Array mit Farbwerten vom linken Spielfeldrand.
     */
    public String[] getLeftBorderColors() {
        return leftBorderColors;
    }

    /**
     * Gibt Farben des rechten Spielfeldrands zurück.
     *
     * @return Array mit Farbwerten vom rechten Spielfeldrand.
     */
    public String[] getRightBorderColors() {
        return rightBorderColors;
    }

    /**
     * Strings welche die Randbilder für die Ränder enthält.
     * Werden verwendet, um die Ränder vom Bild darzustellen.
     * Anhand des Methodennamens wird klar, für welchen Rand die Bilder verwendet werden.
     */
    String[] topBorderImages = {
            "/gui/tiles/NNGN.png",
            "/gui/tiles/NNRN.png",
            "/gui/tiles/NNYN.png"
    };

    String[] bottomBorderImages = {
            "/gui/tiles/GNNN.png",
            "/gui/tiles/RNNN.png",
            "/gui/tiles/YNNN.png"
    };

    String[] leftBorderImages = {
            "/gui/tiles/NGNN.png",
            "/gui/tiles/NRNN.png",
            "/gui/tiles/NYNN.png"
    };

    String[] rightBorderImages = {
            "/gui/tiles/NNNG.png",
            "/gui/tiles/NNNR.png",
            "/gui/tiles/NNNY.png"
    };





    /**
     * Für Testzwecke gedacht. Erstellt ein mitten im Spielgeschehen befindliches Spiel
     * und nimmt das Spielfeld in Form eines StringArrays (äguivalent zur Spielstandsdatei) entgegen.
     *
     * @param tiles     die Mosaikteile auf dem Spielfeld
     */
    public void createBoard(MosaicTile[][] tiles) {
        for (int row = 0; row < tiles.length; row++) {
            for (int column = 0; column < tiles[row].length; column++) {
                MosaicTile tile = tiles[row][column];
                if (tile != null) {
                    cells[row][column].placeTile(tile, Rotation.DEGREE_0);
                }
            }
        }
    }

    /**
     * Generiert zufällig Löcher im Spiel, es mehr als 24 Zellen im Spielfeld gibt.
     *
     * @param row      Variable für die Reihe
     * @param column   Variable für die Spalte
     * @return         Anzahl an Löcher gespeichert im Array
     */
    public boolean[][] generateHoles(int row, int column) {
        int boardRows = row - 2;
        int boardCols = column - 2;
        int totalCells = boardRows * boardCols;

        int maxTiles = 24;
        int numHoles = Math.max(0, totalCells - maxTiles);

        boolean[][] holes = new boolean[row][column];
        Random random = new Random();

        while (numHoles > 0) {
            int r = random.nextInt(boardRows);
            int c = random.nextInt(boardCols);

            if (!holes[r + 1][c + 1]) {
                holes[r + 1][c + 1] = true;
                numHoles--;
            }
        }
        return holes;
    }

    /**
     * Methode um die Löcher aus dem Spielfeld herauszufinden.
     * @return ein boolescher-Array mit den Löchern im Spielfeld.
     */
    public boolean[][] getHoles() {
        boolean[][] holes = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                holes[row][column] = cells[row][column].isHole();
            }
        }
        return holes;
    }

    /**
     * Schaut sich die Position einer leeren Zelle an und gibt zurück, ob dort ein Mosaikteil reinpasst.
     *
     * @param pos   die Position wo das Mosaikteil platziert werden soll.
     * @return      True, wenn das Mosaikteil an der Position passt, ansonsten false.
     */
    public boolean isPositionValid(Position pos) {
        int row = pos.row();
        int column = pos.column();

        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return false;
        }

        return !cells[row][column].isHole() && !cells[row][column].isPlaced();
    }

    /**
     * Methode, welche sich anschaut, ob ein platziertes Mosaikteil mit seinen Nachbarn passt.
     *
     * @param tile      das Mosaikteil, das platziert wird.
     * @param rotation  die derzeitige Rotation vom Mosaikteil.
     * @param pos       die Position, wo das Mosaikteil platziert werden soll.
     * @return          True, wenn das Mosaikteil mit seinen Nachbarn passt, ansonsten false.
     */
    public boolean fitsNeighbours(MosaicTile tile, Rotation rotation, Position pos) {
        Color[] tileColors = tile.getColors(rotation);

        int[][] directions = {
                {-1, 0}, // oberer Nachbar
                {1, 0},  // unterer Nachbar
                {0, -1}, // linker Nachbar
                {0, 1}   // rechter Nachbar
        };

        for (int i = 0; i < 4; i++) {
            int newRow = pos.row() + directions[i][0];
            int newCol = pos.column() + directions[i][1];

            // Überprüfung, ob Nachbar
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns) {
                BoardCell neighbourCell = cells[newRow][newCol];

                if (neighbourCell.isPlaced()) {
                    Color[] neighbourColors = neighbourCell.getTile().getColors(neighbourCell.getRotation());
                    int oppositeSide = (i + 2) % 4;

                    if (!tileColors[i].equals(neighbourColors[oppositeSide]) && neighbourColors[oppositeSide] != Color.GRAY) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Ein Array, welches zufällige Randfarben generiert.
     *
     * @param count     Anzahl der zu erzeugenden Farben.
     * @param imagePath Bildpfad für die Randfarben.
     * @param random    Zufällige Generierung der Randfarben.
     * @return          Ein Array mit zufällig generierten Farben.
     */
    private String[] initRandomColors(int count, String[] imagePath, Random random) {
        String[] images = new String[count];

        for (int i = 0; i < count; i++) {
            images[i] = imagePath[random.nextInt(imagePath.length)];
        }
        return images;
    }

    /**
     * Methode, welche bei einer gespeicherten Datei
     * die richtigen Randfarben wiedergeben soll.
     *
     * @return ein String aus den gespeicherten Randfarben.
     *
     * // TODO Beenden.
     */
    private String[] initSavedColors() {
        return initSavedColors();
    }

    /**
     * Schaut sich an, ob das MosaikTile irgendwo auf dem Spielfeld passt.
     *
     * @param tile  das Mosaikteil, welches nach einer freien Position sucht.
     * @return      True, wenn es irgendwo auf dem Spielfeld passt, ansonsten false.
     */
    public boolean doesTileFitAnywhere(MosaicTile tile) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
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
     * Fügt ein Mosaikteil an einer Position hinzu.
     *
     * @param mosaicTile    das Mosaik-Tile welches an einer Position hinzugefügt werden soll.
     * @param rotation      die derzeitige Rotation vom Mosaikteil
     * @param pos           die Position, wo das Mosaikteil platziert werden soll.
     */
    public void placeTileAt(MosaicTile mosaicTile, Rotation rotation, Position pos) {
        cells[pos.row()][pos.column()].placeTile(mosaicTile, rotation);
    }

}
