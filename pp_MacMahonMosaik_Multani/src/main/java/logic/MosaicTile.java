package logic;

/**
 * Repräsentiert ein Mosaik-Tile, welches auf dem Spielfeld platziert werden kann.
 * Visuelle Darstellung eines Mosaik-Tiles erfolgt über einem PNG-Bild.
 *
 * @author Maurice Singh Multani
 */
public enum MosaicTile {

    GGGG("tiles/GGGG.png"),
    GGRR("tiles/GGRR.png"),
    GRGR("tiles/GRGR.png"),
    GRRR("tiles/GRRR.png"),
    GRYG("tiles/GRYG.png"),
    GRYR("tiles/GRYR.png"),
    GYRG("tiles/GYRG.png"),
    GYYY("tiles/GYYY.png"),

    HHHH("tiles/HHHH.png"),
    NNNN("tiles/NNNN.png"),

    RGGG("tiles/RGGG.png"),
    RGYG("tiles/RGYG.png"),
    RGYR("tiles/RGYR.png"),
    RRRR("tiles/RRRR.png"),
    RRYY("tiles/RRYY.png"),
    RYGR("tiles/RYGR.png"),
    RYGY("tiles/RYGY.png"),
    RYYY("tiles/RYYY.png"),

    YGGG("tiles/YGGG.png"),
    YGRY("tiles/YGRY.png"),
    YGYG("tiles/YGYG.png"),
    YRGY("tiles/YRGY.png"),
    YRRR("tiles/YRRR.png"),
    YRYR("tiles/YRYR.png"),
    YYGG("tiles/YYGG.png"),
    YYYY("tiles/YYYY.png");

    /**
     * Das Bild, dass das Mosaik-Teil visuell darstellt.
     */
    private final String tileImage;

    /**
     * Konstruktor zur Initialisierung des Mosaik-Tiles mit einem Bild.
     * @param tileImage das Bild, das das Mosaik-Tile darstellt.
     */
    MosaicTile(String tileImage) {
        this.tileImage = tileImage;
    }



    /**
     * rotiert die Mosaikzelle um 90° im Uhrzeigersinn.
     */
    public void rotateTile(){

    }



}
