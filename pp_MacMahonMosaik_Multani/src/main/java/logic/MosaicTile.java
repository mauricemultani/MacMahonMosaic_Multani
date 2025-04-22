package logic;

/**
 * Repräsentiert ein Mosaik-Tile, welches auf dem Spielfeld platziert werden kann.
 * Visuelle Darstellung eines Mosaik-Tiles erfolgt über einem PNG-Bild.
 *
 * @author Maurice Singh Multani
 */
public enum MosaicTile {

    GGGG,
    GGRR,
    GRGR,
    GRRR,
    GRYG,
    GRYR,
    GYRG,
    GYYY,

    HHHH,
    NNNN,

    RGGG,
    RGYG,
    RGYR,
    RRRR,
    RRYY,
    RYGR,
    RYGY,
    RYYY,

    YGGG,
    YGRY,
    YGYG,
    YRGY,
    YRRR,
    YRYR,
    YYGG,
    YYYY;

    /**
     * rotiert die Mosaikzelle um 90° im Uhrzeigersinn.
     */
    public void rotateTile(){

    }



}
