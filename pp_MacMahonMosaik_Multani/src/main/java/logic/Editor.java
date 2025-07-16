package logic;

import com.google.gson.Gson;

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
     * Methode zur Anpassung der Größe des Spielfeldes.
     * Spielfeld muss mind. 2 und max. 6 Zellen hoch und breit sein.
     * Muss rechteckig, aber nicht quadratisch sein.
     */
    public void changeSizeOfGameField(int rows, int columns){
        // Exception, falls das Board weniger als 4 Zeilen oder Spalten hat
        if (rows < 4 || columns < 4) {
            throw new IllegalArgumentException("Das Feld muss mindestens 4 Zeilen und Spalten haben");
        }

        // Exception, falls das Board mehr als 8 Zeilen und Spalten hat
        if (rows > 8 || columns > 8) {
            throw new IllegalArgumentException("Das Feld darf maximal 8 Zeilen und Spalten haben");
        }

        Board newBoard = new Board(board.getRows(), board.getColumns(), false);
        this.board = newBoard;
        setBoard(newBoard);
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

        int maxTiles = 24;
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
     * Prüft, ob ein Loch an der angegebenen Stelle ist, oder nicht.
     * @param row   Die Reihe
     * @param col   Die Spalte
     * @return      True, wenn dort ein Loch ist, ansonsten false
     */
    public boolean isHoleAt(int row, int col) {
        return board.getCell(row, col).isHole();
    }

    /**
     * Der Wechsel zurück in den Spielmodus.
     *
     * Findet bei folgenden Bedingungen statt:
     * 1. Alle Ränder haben eine Farbe.
     * 2. Es müssen ausreichend Löcher vorhanden sein. (zutreffend bei mehr als 24 Zellen)
     * 3. Puzzle muss lösbar sein.
     */
    public void switchBackToGameMode(){

    }

    /**
     * Methode welche prüft, ob ein Wechsel zurück in den Spielmodus möglich ist.
     *
     * Folgende Bedingungen müssen für einen Wechsel in den Spielmodus erreicht sein:
     * 1. Alle Ränder haben eine Farbe.
     * 2. Es müssen ausreichend Löcher vorhanden sein.
     * 3. Puzzle muss lösbar sein.
     */
    public boolean canSwitchBackToGameMode(boolean allowed) {


        return allowed;
    }

    /**
     * Prüft, ob Löcher platziert wurden oder nicht.
     */
    public boolean checkIfHolesNeeded() {
        int gameFieldRows = board.getRows() - 2;
        int gameFieldCols = board.getColumns() - 2;
        int totalCells = gameFieldRows * gameFieldCols;

        return totalCells <= 24;
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
}
