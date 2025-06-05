package logic;

/**
 * Die Klasse BoardCell. Sie stellt eine Zelle im Spielfeld dar.
 */
public class BoardCell {

    private MosaicTile tile;
    private Rotation rotation;
    private final boolean isHole;

    public BoardCell(MosaicTile tile, Rotation rotation, boolean isHole) {
        this.tile = tile;
        this.rotation = rotation;
        this.isHole = isHole;
    }

    public boolean isHole() {
        return isHole;
    }

    public MosaicTile getTile() {
        return tile;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Rotation rotate() {
        this.rotation = rotation.next();
        return rotation;
    }

    public void placeTile(MosaicTile tile, Rotation rotation) {
        if (!isHole()) {
            this.tile = tile;
            this.rotation = rotation;
        }
    }

    public boolean isPlaced() {
        return tile != null;
    }
}
