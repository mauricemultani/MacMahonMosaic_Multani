package logic;

public class PlacedTile {

    private final MosaicTile tile;
    private Rotation rotation;

    public PlacedTile(MosaicTile tile, Rotation rotation){
        this.tile = tile;
        this.rotation = rotation;
    }

    public MosaicTile getTile() {
        return tile;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Rotation rotate(){
        return rotation = rotation.next();
    }
}
