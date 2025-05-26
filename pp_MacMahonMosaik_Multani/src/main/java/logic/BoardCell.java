    package logic;

    /**
     * Die Klasse BoardCell. Sie stellt eine Zelle im Spielfeld dar.
     */
    public class BoardCell {

        private MosaicTile tile;
        private Rotation rotation;
        private final Boolean isHole;

        public BoardCell(MosaicTile tile, Rotation rotation, Boolean isHole) {
            this.tile = tile;
            this.rotation = rotation;
            this.isHole = isHole;
        }

        public Boolean isHole() {
            return isHole;
        }

        public MosaicTile getTile() {
            return tile;
        }

        public Rotation getRotation() {
            return rotation;
        }

        public Rotation rotate() {
            return rotation = rotation.next();
        }

        public void placeTile(MosaicTile tile, Rotation rotation) {
            if (!isHole()) {
                this.tile = tile;
                this.rotation = rotation;
            }
        }

        public Boolean isPlaced() {
            return tile != null;
        }
    }
