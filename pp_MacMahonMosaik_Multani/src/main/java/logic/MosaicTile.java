package logic;

import java.awt.*;

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
    YYYY,

    HHHH,
    NNNN;

    /**
     * Gibt den Pfad zur Bilddatei zurück, die dieses MosaikTile repräsentiert.
     * @return Pfad zur Bilddatei.
     */
    public String getImagePath() {
        return "/gui/tiles/" + this.name() + ".png";
    }

    /**
     *  Trennt, die einzelnen Farben von einem Mosaikteil.
     */
    public Color[] getColors() {
        Color[] colors = new Color[4];
        String name = this.name();

        for (int i = 0; i < 4; i++){
            char c = name.charAt(i);
            switch (c) {
                case 'Y' -> colors[i] = Color.YELLOW;
                case 'R' -> colors[i] = Color.RED;
                case 'G' -> colors[i] = Color.GREEN;
                default -> throw new UnsupportedOperationException("Unknown Color:" + c);
            }
        }
        return colors;
    }

    public Color[] getColors(Rotation rotation){
        Color[] original = this.getColors();
        Color[] rotated = new Color[4];

        for (int i = 0; i < 4; i++) {
            rotated[i] = original[(i - rotation.getRotation() + 4) % 4];
        }
        return rotated;
    }
}
