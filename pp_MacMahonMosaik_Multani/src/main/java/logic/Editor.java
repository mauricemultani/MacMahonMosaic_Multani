package logic;

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
    private final Board board;

    /**
     * Konstruktor, welches ein GridPane initialisiert.
     * @param board     Das Spielfeld.
     */
    public Editor(Board board) {
        this.board = board;
    }

    /**
     * Wechsel vom Spielmodus in den Editormodus.
     *
     * Nimmt alle Teile vom Feld und lässt nur noch Rand und ggf. Löcher übrig.
     */
    public void switchToEditorMode(){
        int rows = board.getRows();
        int columns = board.getColumns();

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < columns - 1; col++) {
                BoardCell cell = board.getCell(row, col);
                if (!cell.isHole())
                    cell.placeTile(MosaicTile.NNNN, Rotation.DEGREE_0);
            }
        }
    }

    /**
     * Methode zur Anpassung der Farben der Ränder (pro Teil)
     */
    public void adjustColorsOfBorder() {

    }

    /**
     * Methode zur Anpassung der Größe des Spielfeldes.
     * Spielfeld muss mind. 2 und max. 6 Zellen hoch und breit sein.
     * Muss rechteckig, aber nicht quadratisch sein.
     */
    public void changeSizeOfGameField(){

    }

    /**
     * Hilfsmethode für changeSizeOfGameField().
     *
     * Spieler bestimmt Positionen der Löcher, wenn dass Spielfeld nach seiner Erstellung
     * mehr als 24 Zellen hat.
     */
    public void choosePositionsOfHoles(){

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
     * Speichert das laufende Spiel.
     *
     */
    public void saveGame(){

    }

    /**
     * Lädt ein gespeichertes Spiel.
     * Wenn ein Puzzle mit Teilen auf dem Spielfeld geladen wird, sollen diese entfernt werden.
     */
    public void loadGame(){

    }
}
