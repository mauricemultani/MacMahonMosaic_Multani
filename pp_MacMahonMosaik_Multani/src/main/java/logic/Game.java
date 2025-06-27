package logic;

import com.google.gson.Gson;

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
     * Konstruktor für ein neues Spiel.
     */
    public Game(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        initializeGame();
    }

    /**
     * Konstruktor für Testzwecke. Erzeugt ein befindliches Spiel mitten im Spielgeschehen.
     * Nimmt das Spielfeld in Form eines BoardCell-Arrays entgegen.
     *
     * @param ongoingGame das laufende Spiel
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

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                // Kann vllt später verwendet werden
                // boolean isHole = board.getCell(row, column).isHole();

                board.getCell(row, column).placeTile(null, Rotation.DEGREE_0);
            }
        }
    }

    /**
     * Klont das Spielfeld.
     * Wird für die Speicherung des Spielfelds verwendet.
     */
    public void cloneGameField() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                ongoingGame[row][column] = board.getCell(row, column);
            }
        }
    }

    /**
     * Speichert das laufende Spiel.
     * Zu beachten wäre, dass es speichert, wenn das Spiel schon einen Namen hat.
     */
    public void saveGame(File file) {
        cloneGameField();

        // Gson-Objekt erstellen
        Gson gson = new Gson();

        // BoardCell Array in JSON-String umwandeln
        String json = gson.toJson(ongoingGame);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Lädt ein gespeichertes Spiel
     */
    public void loadGame(File file) {


        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            ongoingGame = gson.fromJson(reader, BoardCell[][].class);

            for (int row = 0; row < ongoingGame.length; row++) {
                for (int col = 0; col < ongoingGame[0].length; col++) {
                    BoardCell cell = ongoingGame[row][col];
                    BoardCell boardCell = board.getCell(row, col);
                    boardCell.placeTile(cell.getTile(), cell.getRotation());
                    boardCell.setHole(cell.isHole());
                }
            }

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


}
