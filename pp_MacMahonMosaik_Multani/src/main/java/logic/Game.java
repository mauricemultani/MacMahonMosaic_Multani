package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.utils.BoardCell;

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

    private final int rows;

    private final int columns;

    /**
     * Ein Array zur Speicherung des aktuellen Spielstands.
     */
    private BoardCell[][] ongoingGame;

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
     * Konvertiert das Spielfeld zu einem String-Array.
     *
     * @return  Spielfeld als String-Array
     */
    private String[][] convertBoardToString() {
        int rows = board.getRows();
        int columns = board.getColumns();
        String[][] converted = new String[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                BoardCell cell = board.getCell(row, col);

                if (cell.isHole()) {
                    converted[row][col] = "HHHH";
                } else if (cell.isPlaced()) {
                    converted[row][col] = cell.getTile().name();
                } else {
                    if (row == 0) {
                        converted[row][col] = board.getTopBorderTiles()[col];
                    } else if (row == rows - 1) {
                        converted[row][col] = board.getBottomBorderTiles()[col];
                    } else if (col == 0) {
                        converted[row][col] = board.getLeftBorderTiles()[row];
                    } else if (col == columns - 1) {
                        converted[row][col] = board.getRightBorderTiles()[row];
                    } else {
                        converted[row][col] = "NNNN";
                    }
                }
            }
        }

        converted[0][0] = "NNNN";
        converted[0][columns - 1] = "NNNN";
        converted[rows - 1][0] = "NNNN";
        converted[rows -1][columns - 1] = "NNNN";

        return converted;
    }

    /**
     * Speichert das laufende Spiel.
     */
    public void saveGame(File file) {
        String[][] field = convertBoardToString();
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
            FieldWrapper wrapper = gson.fromJson(reader, FieldWrapper.class);

            String[][] field = wrapper.field;

            int rows = field.length;
            int cols = field[0].length;

            if (rows >= 4 && rows <= 8 && cols >= 4 && cols <= 8) {
                this.board = new Board(rows, cols, field, false);

                setBoard(board);
            } else {
                throw new IllegalArgumentException("Die Spielfeldgröße muss 2x2 bis 6x6 sein.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Eine extra Klasse in Game. Diese ist dafür gedacht, dass bei einer Speicherung das Spielfeld formatierter aussieht.
     *
     * @author Maurice Singh Multani
     */
    public static class FieldWrapper {
        public String[][] field;

        public FieldWrapper(String[][] field) {
            this.field = field;
        }
    }

}
