package logic;

import com.google.gson.Gson;
import logic.utils.BoardCell;
import logic.utils.MosaicTile;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

/**
 * Der Editormodus. Der Spieler hat mit dem Editormodus die Möglichkeit sein eigenes Spiel zu erstellen.
 * Implementiert das Interface "Solve", welches Methoden zur Überprüfung der Lösbarkeit des Puzzles bereitstellt.
 *
 * @author Maurice Singh Multani
 */
public class Editor {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    private Board board;

    /**
     * Konstruktor, welches ein GridPane initialisiert.
     * @param board     Das Spielfeld.
     */
    public Editor(Board board) {
        this.board = board;
    }

    /**
     * Gibt das Spielfeld zurück.
     * @return      das Spielfeld.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Setzt das Spielfeld.
     * @param board     das neue Spielfeld.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Die Methode soll bei einem Switch in den Editor-Modus alles
     * außer den Rändern und Löchern leeren.
     */
    public void switchToEditorMode() {
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < columns - 1; col++) {
                BoardCell cell = board.getCell(row, col);
                if (cell.isPlaced() && !cell.isHole()) {
                    cell.placeTile(null, null);
                }
            }
        }
    }

    public MosaicTile getNextBorderColor(MosaicTile currTile, List<MosaicTile> availableTiles) {
        int index = availableTiles.indexOf(currTile);
        int nextIndex = (index + 1) % availableTiles.size();

        return availableTiles.get(nextIndex);
    }

    /**
     * Methode zur Anpassung der Farben der Ränder (pro Teil)
     */
    public void nextBorderColor(int index, String[] borderArray, MosaicTile tile) {
        borderArray[index] = tile.getImagePath();
    }

    /**
     * Hilfsmethode für changeSizeOfGameField().
     *
     * Spieler bestimmt Positionen der Löcher, wenn dass Spielfeld nach seiner Erstellung
     * mehr als 24 Zellen hat.
     *
     * @param rows  die Reihenanzahl
     * @param cols  die Spaltenanzahl
     */
    public int calculateIfHolesNeeded(int rows, int cols){
        int gameFieldRows = rows - 2;
        int gameFieldCols = cols - 2;
        int totalCells = gameFieldRows * gameFieldCols;
        int holes = 0;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (board.isHole(row, col)) {
                    holes++;
                }
            }
        }
        int maxTiles = 24;

        totalCells = totalCells - holes;

        return Math.max(0, totalCells - maxTiles);
    }

    public boolean needsHoles(int rows, int cols) {
        return calculateIfHolesNeeded(rows, cols) > 0;
    }

    /**
     * Platziert ein Loch an der angegebenen Stelle.
     * @param row   Die Reihe
     * @param col   Die Spalte
     */
    public void placeHoleAt(int row, int col) {
        if (row > 0 && row < board.getRows() - 1 &&
                col > 0 && col < board.getColumns() - 1) {
            board.getCell(row, col).setHole();
        }
    }

    /**
     * Lädt ein gespeichertes Spiel.
     * Wenn ein Puzzle mit Teilen auf dem Spielfeld geladen wird,
     * sollen diese entfernt werden.
     */
    public void loadGame(File file){
        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Game.FieldWrapper wrapper = gson.fromJson(reader, Game.FieldWrapper.class);

            String[][] field = wrapper.field;

            int rows = field.length;
            int cols = field[0].length;

            this.board = new Board(rows, cols, field, true);

            setBoard(board);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Die Methode soll jedes Mosaikteil durchgehen und schauen,
     * ob an einer Stelle ein Mosaikteil passt oder nicht.
     *
     * Für Lösbarkeitsüberprüfung & help to Solve
     */
    public void checkSolvability() {
        //TODO Entweder enhanced for Schleife oder normale for Schleife für das Mosaikteil.
        // Notiz: Um Zeit zu sparen, dem Spieler bei einem platzierten Mosaikteil
        // auch die Rotation mitgeben, damit er/sie das Bild dementsprechend auch rotieren kann.

        // Bild wird (wahrscheinlich) nicht mit Rotation in der leeren Zelle
        // eingefügt, da die Enum Konstanten dafür nicht definiert sind.
    }
}
