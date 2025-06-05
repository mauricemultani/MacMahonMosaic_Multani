package logic;

import javafx.scene.layout.GridPane;

/**
 * Repräsentiert das Spiel.
 * Verwaltet das Spielfeld, die Platzierung der Mosaikteile und den aktuellen Spielstand.
 * Implementiert das Interface "Solvability", welches Methoden zur Überprüfung der Lösbarkeit des Puzzles bereitstellt.
 * Die Klasse ist verantwortlich für die Spiellogik, Initialisieren und Verwalten des Spiels.
 *
 * @author Maurice Singh Multani
 */
public class Game {

    private MosaicTile tile;
    private BoardCell cell;
    private Board board;
    private Position pos;
    private Rotation rotation;

    /**
     * Das GridPane auf dass, das Spielfeld dargestellt wird.
     */
    private GridPane gameField;

    /**
     * Ein Array zur Speicherung des aktuellen Spielstands.
     */
    private BoardCell[][] ongoingGame;

    /**
     * Konstruktor für ein neues Spiel.
     */
    public Game(){
        int rows = 5;
        int columns = 5;

        initializeGame(rows, columns);
    }

    /**
     * Konstruktor für Testzwecke. Erzeugt ein befindliches Spiel mitten im Spielgeschehen.
     * Nimmt das Spielfeld in Form eines StringArrays entgegen.
     * @param ongoingGame das laufende Spiel
     */
    public Game(BoardCell[][] ongoingGame){
        this.ongoingGame = ongoingGame;
    }

    /**
     * Hier findet der Prozess vom Spiel statt.
     *
     */
    public void macMahonGame(){
        board.placeTileAt(tile, rotation, pos);
        if (cell.isPlaced() && board.isPositionValid(pos)) {

        }
    }

    /**
     * Initialisiert das Spiel.
     * Löscht alle Zellen und setzt das Spielfeld zurück.
     */
    public void initializeGame(int rows, int columns) {
        this.board = new Board(rows, columns);

        this.ongoingGame = new BoardCell[rows][columns];

        this.gameField = new GridPane();

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
     * Klont das Spielfeld. Wird für den Wechsel in den Editor-Modus benötigt.
     *
     * Bisher noch unklar, ob es benötigt wird oder nicht.
     */
    public void cloneGameField() {
        BoardCell[][] copy = new BoardCell[ongoingGame.length][ongoingGame[0].length];
        for (int row = 0; row < ongoingGame.length; row++){
            for (int column = 0; column < ongoingGame[0].length; column++) {
                BoardCell orig = ongoingGame[row][column];
                copy[row][column] = new BoardCell(
                        orig.getTile(),
                        orig.getRotation(),
                        orig.isHole()
                );
            }
        }
    }

    /**
     * Speichert das laufende Spiel.
     *
     * Zu beachten wäre, dass es speichert, wenn das Spiel schon einen Namen hat.
     */
    public void saveGame(){

    }

    /**
     * Lädt ein gespeichertes Spiel
     */
    public void loadGame(){

    }

    /**
     * Benachrichtigt dem Spieler, dass er das Spiel gewonnen hat.
     * Der Spieler wird erst dann benachrichtigt, wenn alle Zellen belegt und die Farb-Regel nicht verletzt wird.
     */
    public void winningGame(){

    }

    /**
     * Dem Spieler wird sichtbar angezeigt, welche Zellen nicht die passenden Farben an den Kanten haben.
     * Soll nur eine Anzeige sein, der Spieler muss den Fehler nicht sofort beheben.
     */
    public void showNotMatchingEdge(){

    }


}
