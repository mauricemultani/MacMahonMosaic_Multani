package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Repräsentiert das Spiel.
 * Verwaltet das Spielfeld, die Platzierung der Mosaikteile und den aktuellen Spielstand.
 * Die Klasse ist verantwortlich für die Spiellogik, Initialisieren und Verwalten des Spiels.
 *
 * @author Maurice Singh Multani
 */
public class Game {


    private Board board;

    private MosaicTile tile;

    private Rotation rotation;

    private final int rows;

    private final int columns;

    /**
     * Ein Array zur Speicherung des aktuellen Spielstands.
     */
    private BoardCell[][] ongoingGame;

    /**
     * Konstruktor für ein neues Spiel.
     */
//    public Game(int rows, int columns) {
//        this.rows = rows;
//        this.columns = columns;
//
//        initializeGame();
//    }

    /**
     * Konstruktor welches das Spielfeld von Board übernimmt.
     * @param board     das Spielfeld, was schon initialisiert, wurde.
     */
    public Game(Board board) {
        this.board = board;
        this.rows = board.getRows();
        this.columns = board.getColumns();
        this.ongoingGame = new BoardCell[rows][columns];
    }

    /**
     * Konstruktor für Testzwecke. Erzeugt ein befindliches Spiel mitten im Spielgeschehen.
     * Nimmt das Spielfeld in Form eines BoardCell-Arrays entgegen.
     *
     * @param rows          die Anzahl an Reihen
     * @param columns       die Anzahl an Spalten
     * @param ongoingGame   das laufende Spiel
     */
    public Game(int rows, int columns, BoardCell[][] ongoingGame) {
        this.rows = rows;
        this.columns = columns;
        this.ongoingGame = ongoingGame;
    }

    /**
     * Hier findet der Prozess vom Spiel statt.
     */
    public void macMahonGame(MosaicTile tile, Rotation rotation, Position pos, BoardCell cell) {
        if (cell.isPlaced() && board.isPositionValid(pos)) {
            board.placeTileAt(tile, rotation, pos);
        }
    }

    /**
     * Initialisiert das Spiel.
     * Löscht alle Zellen und setzt das Spielfeld zurück.
     */
    public void initializeGame() {
        this.board = new Board(rows, columns);

        this.ongoingGame = new BoardCell[rows][columns];

        clearBoard();
    }

    /**
     * Entfernt alle bereits belegten Zellen und setzt das Spielfeld zurück.
     */
    public void clearBoard() {
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int row = 1; row < rows - 1; row++) {
            for (int column = 1; column < columns - 1; column++) {
                // Kann vllt später verwendet werden
                // boolean isHole = board.getCell(column, row).isHole();

                board.getCell(row, column).placeTile(null, Rotation.DEGREE_0);
            }
        }
    }

    /**
     * Klont das Spielfeld.
     * Wird für die Speicherung des Spielfelds verwendet.
     */
    private BoardCell[][] saveCells() {
        BoardCell[][] saveCells = new BoardCell[board.getRows()][board.getColumns()];

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                BoardCell cell = board.getCell(row, col);

                    if (cell.isHole()) {
                        saveCells[row][col] = new BoardCell(true);
                    } else if (cell.getTile() != null) {
                        saveCells[row][col] = new BoardCell(cell.getTile());
                    } else {
                        saveCells[row][col] = new BoardCell(null);
                    }
            }
        }

        return saveCells;
    }

    private String[][] convertBoardToString(BoardCell[][] cells) {
        int rows = board.getRows();
        int columns = board.getColumns();
        String[][] converted = new String[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                BoardCell cell = cells[row][col];

                if (cell.isHole()) {
                    converted[row][col] = "HHHH";
                } else if (cell.getTile() != null) {
                    converted[row][col] = cell.getTile().name();
                } else {
                    converted[row][col] = "NNNN";
                }
            }
        }
        return converted;
    }

    /**
     * Speichert das laufende Spiel.
     */
    public void saveGame(File file) {
        String[][] field = convertBoardToString(saveCells());
        FieldWrapper wrapper = new FieldWrapper(field);

        // Gson-Objekt erstellen
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // BoardCell Array in JSON-String umwandeln
        String json = gson.toJson(wrapper);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lädt ein gespeichertes Spiel
     *
     * Achten auf Fehlermeldungen
     * Bsp.: falsche Zeilen/Spalten geladen
     *       keine JSON datei
     *
     */
    public void loadGame(File file) {
        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            BoardCell[][] savedCells = gson.fromJson(reader, BoardCell[][].class);

            this.board = new Board(rows, columns, savedCells);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Benachrichtigt dem Spieler, dass er das Spiel gewonnen hat.
     * Der Spieler wird erst dann benachrichtigt, wenn alle Zellen belegt und die Farb-Regel nicht verletzt wird.
     */
    public void winningGame() {

    }

    /**
     * Dem Spieler wird sichtbar angezeigt, welche Zellen nicht die passenden Farben an den Kanten haben.
     * Soll nur eine Anzeige sein, der Spieler muss den Fehler nicht sofort beheben.
     */
    public void showNotMatchingEdge() {

    }


    public static class FieldWrapper {
        public String[][] field;

        public FieldWrapper(String[][] field) {
            this.field = field;
        }
    }

}
