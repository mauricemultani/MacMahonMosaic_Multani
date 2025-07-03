package logic;

/**
 * Die Klasse BoardCell. Sie stellt eine Zelle im Spielfeld dar.
 */
public class BoardCell {

    private MosaicTile tile;
    private Rotation rotation;
    private boolean hole;

    public BoardCell(MosaicTile tile) {
        this.tile = tile;
    }

    public BoardCell(boolean isHole) {
        this.hole = isHole;
        if (isHole) {
            this.tile = MosaicTile.HHHH;
        }
    }

    public MosaicTile getTile() {
        return tile;
    }



    public void setTile(MosaicTile tile) {
        this.tile = tile;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public boolean isHole() {
        return hole;
    }

    public void setHole() {
        this.hole = true;
        this.tile = MosaicTile.HHHH;
    }

    public Rotation rotate() {
        this.rotation = rotation.next();
        return rotation;
    }

    public void placeTile(MosaicTile tile, Rotation rotation) {
        if (!hole) {
            this.tile = tile;
            this.rotation = rotation;
        }
    }

    public boolean isPlaced() {
        return tile != null;
    }

}
