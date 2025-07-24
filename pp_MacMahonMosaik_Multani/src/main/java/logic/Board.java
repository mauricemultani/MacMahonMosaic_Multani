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
    public Board(int rows, int columns, boolean withHoles) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new BoardCell[rows][columns];

        if (withHoles) {
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

        } else {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    this.cells[row][col] = new BoardCell(null);
                }
            }

            this.topBorderColors = initRandomColors(columns, topBorderImages, random);
            this.bottomBorderColors = initRandomColors(columns, bottomBorderImages, random);
            this.leftBorderColors = initRandomColors(rows, leftBorderImages, random);
            this.rightBorderColors = initRandomColors(rows, rightBorderImages, random);
        }
    }

    /**
     * Konstruktor um ein gespeichertes Spiel zu laden.
     *
     * @param rows          die Anzahl an Reihen
     * @param columns       die Anzahl an Spalten
     * @param field         das Spielfeld
     */
    public Board(int rows, int columns, String[][] field, boolean forEditor) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new BoardCell[rows][columns];

        if (!forEditor) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                String tileName = field[row][column];

                if ("HHHH".equals(tileName)) {
                    this.cells[row][column] = new BoardCell(true);
                } else if ("NNNN".equals(tileName)) {
                    this.cells[row][column] = new BoardCell(null);
                } else {
                    try {
                        MosaicTile tile = MosaicTile.valueOf(tileName);
                        this.cells[row][column] = new BoardCell(tile);
                        this.cells[row][column].setRotation(Rotation.DEGREE_0);
                    } catch (IllegalArgumentException e) {
                        this.cells[row][column] = new BoardCell(null);
                    }
                }
            }
        }

        this.topBorderColors = initSavedColors(extractTopBorderTiles(field));
        this.bottomBorderColors = initSavedColors(extractBottomBorderTiles(field));
        this.leftBorderColors = initSavedColors(extractLeftBorderTiles(field));
        this.rightBorderColors = initSavedColors(extractRightBorderTiles(field));
        } else {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    String tileName = field[row][col];
                    if ("HHHH".equals(tileName)) {
                        this.cells[row][col] = new BoardCell(true);
                    } else {
                        this.cells[row][col] = new BoardCell(null);
                    }
                }
            }

            this.topBorderColors = initSavedColors(extractTopBorderTiles(field));
            this.bottomBorderColors = initSavedColors(extractBottomBorderTiles(field));
            this.leftBorderColors = initSavedColors(extractLeftBorderTiles(field));
            this.rightBorderColors = initSavedColors(extractRightBorderTiles(field));
        }
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
     *
     */
    public boolean isHole(int row, int col) {
        return cells[row][col].isHole();
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
     * Gibt die Randfarben als Mosaikteile zurück.
     * Sie trennt den Bildpfad so, dass nur noch das Mosaikteil übrig bleibt.
     *
     * @param borderTiles   Array mit Bildpfad vom Rand
     * @return              Array, welches nur noch die Mosaikteile hat.
     */
    private String[] getBorderTiles(String[] borderTiles) {
        String[] tiles = new String[borderTiles.length];

        for (int i = 0; i < borderTiles.length; i++) {
            String filename = borderTiles[i].substring(borderTiles[i].lastIndexOf("/") + 1);
            tiles[i] = filename.replace(".png", "");
        }
        return tiles;
    }

    /**
     * Gibt die Randfarben als Mosaikteile zurück.
     * Die Namen der Methoden geben an zu welchen Rändern die Randfarben gehören.
     *
     * @return  Ein Array von den Mosaikteilen, die von ihren Bildpfaden getrennt wurden.
     */
    public String[] getTopBorderTiles() {
        return getBorderTiles(topBorderColors);
    }

    public String[] getBottomBorderTiles() {
        return getBorderTiles(bottomBorderColors);
    }

    public String[] getLeftBorderTiles() {
        return getBorderTiles(leftBorderColors);
    }

    public String[] getRightBorderTiles() {
        return getBorderTiles(rightBorderColors);
    }

    /**
     *  Hilfsmethoden, welche zum Extrahieren der Randbilder verwendet werden.
     *  Die Namen der Methoden geben an zu welchen Rändern die Randfarben gehören.
     *
     * @param field     das Spielfeld
     * @return          die jeweiligen Ränder
     */
    private String[] extractTopBorderTiles(String[][] field) {
        return field[0];
    }

    private String[] extractBottomBorderTiles(String[][] field) {
        return field[rows - 1];
    }

    private String[] extractLeftBorderTiles(String[][] field) {
        String[] leftBorder = new String[rows];
        for (int i = 0; i < rows; i++) {
            leftBorder[i] = field[i][0];
        }
        return leftBorder;
    }

    private String[] extractRightBorderTiles(String[][] field) {
        String[] rightBorder = new String[rows];
        for (int i = 0; i < rows; i++) {
            rightBorder[i] = field[i][columns - 1];
        }
        return rightBorder;
    }

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

        int[] tileSide = {0, 2, 3, 1};
        // Die gegenüber liegenden Mosaikteil in ihren Himmelsrichtungen
        int[] oppositeSides = {2, 0, 1, 3};

        for (int i = 0; i < 4; i++) {
            int newRow = pos.row() + directions[i][0];
            int newCol = pos.column() + directions[i][1];
            int tileIndex = tileSide[i];

            // Überprüfung, ob Nachbar richtig angrenzende Farben haben
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns) {
                BoardCell neighbourCell = cells[newRow][newCol];

                if (neighbourCell.isPlaced()) {
                    Color[] neighbourColors = neighbourCell.getTile().getColors(neighbourCell.getRotation());
                    int neighbourIndex = oppositeSides[i];

                    if (neighbourColors[neighbourIndex] != Color.GRAY && !tileColors[tileIndex].equals(neighbourColors[neighbourIndex])) {
                        return false;
                    }
                }
            }
        }

        if (!fitsBorderNeighbours()) {
            return false;
        }

        return true;
    }

    /**
     * Methode, welche sich die Platzierung der Randzellen und ihren Nachbarzellen anschaut.
     * @return  true, wenn alle Nachbarzellen und Randzellen übereinstimmen, ansonsten false.
     */
    private boolean fitsBorderNeighbours() {
        // Überprüfung, ob die oberen Randzellen passen
        for (int col = 0; col < columns; col++) {
            // holt sich die Mosaikteile, die an den Rändern platziert sind
            BoardCell cell = getCell(1, col);

            if (cell.isPlaced()) {
                String borderTile = getTopBorderTiles()[col];

                Color borderingColor = MosaicTile.valueOf(borderTile).getColors()[2];

                Color tileColor = cell.getTile().getColors(cell.getRotation())[0];

                if (tileColor != Color.GRAY && !tileColor.equals(borderingColor)) {
                    return false;
                }
            }
        }

        // Überprüfung, ob die unteren Randzellen passen
        for (int col = 0; col < columns; col++) {
            BoardCell cell = getCell(rows - 2, col);
            if (cell.isPlaced()) {
                String borderTile = getBottomBorderTiles()[col];

                Color borderingColor = MosaicTile.valueOf(borderTile).getColors()[0];

                Color tileColor = cell.getTile().getColors(cell.getRotation())[2];

                if (tileColor != Color.GRAY && !tileColor.equals(borderingColor)) {
                    return false;
                }
            }
        }

        // Überprüfung, ob die linken Randzellen passen
        for (int row = 0; row< rows; row++) {
            BoardCell cell = getCell(row, 1);
            if (cell.isPlaced()) {
                String borderTile = getLeftBorderTiles()[row];

                Color borderingColor = MosaicTile.valueOf(borderTile).getColors()[1];

                Color tileColor = cell.getTile().getColors(cell.getRotation())[3];

                if (tileColor != Color.GRAY && !tileColor.equals(borderingColor)) {
                    return false;
                }
            }
        }

        // Überprüfung, ob die rechten Randzellen passen
        for (int row = 0; row < rows; row++) {
            BoardCell cell = getCell(row, columns - 2);
            if (cell.isPlaced()) {
                String borderTile = getRightBorderTiles()[row];

                Color borderingColor = MosaicTile.valueOf(borderTile).getColors()[3];

                Color tileColor = cell.getTile().getColors(cell.getRotation())[1];

                if (tileColor != Color.GRAY && !tileColor.equals(borderingColor)) {
                    return false;
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
     * die richtigen Randfarben initialisieren soll.
     *
     * @param tiles     Array mit Randfarben
     * @return          ein String aus den gespeicherten Randfarben.
     */
    private String[] initSavedColors(String[] tiles) {
        String[] paths = new String[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            paths[i] = "/gui/tiles/" + tiles[i] + ".png";
        }
        return paths;
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

                if (isPositionValid(pos)) {
                    for (Rotation rotation : Rotation.values()) {
                        if (fitsNeighbours(tile, rotation, pos)) {
                            placeTileAt(tile, rotation, pos);
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
        BoardCell cell = cells[pos.row()][pos.column()];
        if (!cell.isHole()) {
            cell.placeTile(mosaicTile, rotation);
        }
    }

    /**
     * Entfernt ein Mosaikteil von einer Position.
     *
     * @param pos die Position, von der das Mosaikteil entfernt werden soll.
     */
    public void removeTileAt(Position pos) {
        cells[pos.row()][pos.column()].placeTile(null, Rotation.DEGREE_0);
    }

    /**
     * Startet das Spiel neu.
     * Alle Teile sollen vom Feld genommen werden. Nur Rand und Löcher bleiben übrig.
     */
    public void restartGame() {
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < columns - 1; col++) {
                BoardCell cell = getCell(row, col);

                if (!cell.isHole()) {
                    cells[row][col] = new BoardCell(null);
                }
            }
        }
    }
}
