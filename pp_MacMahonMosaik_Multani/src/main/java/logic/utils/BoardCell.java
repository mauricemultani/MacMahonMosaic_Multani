package logic.utils;

/**
 * Die Klasse BoardCell. Sie stellt eine Zelle im Spielfeld dar.
 */
public class BoardCell {

    private MosaicTile tile;
    private Rotation rotation;
    private boolean hole;

    /**
     * Erstellt ein neues Mosaikteil bzw. eine neue Zelle.
     * @param tile  das Mosaikteil
     */
    public BoardCell(MosaicTile tile) {
        this.tile = tile;
    }

    /**
     * Erstellt ein Loch, sofern true.
     * @param isHole    wenn true, dann ist es ein Loch, ansonsten false.
     */
    public BoardCell(boolean isHole) {
        this.hole = isHole;
        if (isHole) {
            this.tile = MosaicTile.HHHH;
        }
    }

    /**
     * Gibt das Mosaikteil zurück.
     * @return  das Mosaikteil
     */
    public MosaicTile getTile() {
        return tile;
    }

    /**
     * Gibt die Rotation zurück.
     * @return  die Rotation.
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Setzt die Rotation eines Mosaikteils.
     * @param rotation  die Rotation die gesetzt wird.
     */
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    /**
     * Gibt zurück, ob eine Zelle ein Loch ist, oder nicht.
     * @return wenn true, dann ist die Zelle ein Loch, ansonsten false.
     */
    public boolean isHole() {
        return hole;
    }

    /**
     * Setzt ein Loch.
     */
    public void setHole() {
        this.hole = true;
        this.tile = MosaicTile.HHHH;
    }

    /**
     * Platziert ein Mosaikteil mit seiner Rotation.
     * @param tile      das Mosaikteil
     * @param rotation  die Rotation des Mosaikteils.
     */
    public void placeTile(MosaicTile tile, Rotation rotation) {
        if (!hole) {
            this.tile = tile;
            if (rotation == null){
                rotation = Rotation.DEGREE_0;
            }
            this.rotation = rotation;
        }
    }

    /**
     * Überprüft, ob eine Zelle schon besetzt ist.
     * @return  true, wenn Zelle besetzt ist, ansonsten false.
     */
    public boolean isPlaced() {
        return tile != null && !hole && tile != MosaicTile.NNNN;
    }

}
