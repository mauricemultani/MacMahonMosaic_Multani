package logic;

/**
 * Stellt das Spielfeld dar.
 *
 * @author Maurice Singh Multani
 */
public class GameField {

    private final static String[][] field = {
            {"NNNN", "NNGN", "NNGN", "NNGN", "NNNN"}, // x = 0
            {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"}, // x = 1
            {"NRNN", "NNNN", "NNNN", "NNNN", "NNNR"},
            {"NGNN", "NNNN", "NNNN", "NNNN", "NNNG"},
            {"NNNN", "YNNN", "GNNN", "YNNN", "NNNN"}
    };

    /**
     * Schaut sich die Position einer leeren Zelle an und gibt zurück, ob dort ein Mosaik-Tile reinpasst.
     * @param mosaicTile das Mosaik-Tile welches an der zur überprüfenden Position eingefügt werden soll.
     * @param row die Reihe der Position.
     * @param col die Spalte der Position.
     * @return Ob an der Position das Mosaik-Tile passt oder nicht.
     */
    public boolean doesTileFitAt(MosaicTile mosaicTile, int row, int col){
        return false;
    }

    /**
     * Schaut sich an, ob das MosaikTile irgendwo auf dem Spielfeld passt.
     * @param gameField das Spielfeld
     * @param mosaicTile das Mosaik-Tile, welches nach einer freien Position sucht.
     * @return Ob das Mosaik-Tile überhaupt im Spielfeld passt.
     */
    public boolean doesTileFitAnywhere(GameField gameField, MosaicTile mosaicTile){
        return false;
    }

    /**
     * Fügt ein Mosaik-Tile an einer Position hinzu.
     * @param mosaicTile das Mosaik-Tile welches an einer Position hinzugefügt werden soll.
     * @param row die Reihe der Position.
     * @param col die Spalte der Position.
     */
    public void placeTileAt(MosaicTile mosaicTile, int row, int col){

    }
}
