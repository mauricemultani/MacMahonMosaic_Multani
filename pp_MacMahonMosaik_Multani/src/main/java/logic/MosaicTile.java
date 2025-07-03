package logic;

import java.awt.*;

/**
 * Repräsentiert ein Mosaikteil, welches auf dem Spielfeld platziert werden kann.
 * Visuelle Darstellung eines Mosaikteils erfolgt über einem PNG-Bild.
 *
 * Die jeweiligen Buchstaben stehen hierbei für bestimmte Farben.
 * G = Green (Grün)
 * Y = Yellow (Gelb)
 * R = Red (Rot)
 *
 * Die Angabe für jede Zelle gibt in einem String mit vier Buchstaben an, welche Farben die Kanten haben.
 * Die Reihenfolge der Buchstaben folgt dabei einem Kompass, d.h. der erste Buchstabe gibt
 * die nördliche (obere) Kante an, der zweite die östliche,
 * der dritte die südliche und der vierte die westliche Kante.
 *
 * Bsp.: GRYG wäre 1. Green, 2. Red, 3. Yellow und 4. Green
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
    NNNN,

    GGGG_ohne_Linien,
    RRRR_ohne_Linien,
    YYYY_ohne_Linien,

    GNNN,
    NGNN,
    NNGN,
    NNNG,

    RNNN,
    NRNN,
    NNRN,
    NNNR,

    YNNN,
    NYNN,
    NNYN,
    NNNY;

    /**
     * Gibt den Pfad zur Bilddatei zurück, die dieses MosaikTile repräsentiert.
     * @return Pfad zur Bilddatei.
     */
    public String getImagePath() {
        return "/gui/tiles/" + this.name() + ".png";
    }

    /**
     * Gibt Farben vom Mosaikteil in Standardrotation (0-Grad) zurück.
     * Farben werden anhand von Buchstaben im Enum bestimmt.
     *
     * @return Ein Array von 4 Farben.
     * @throws UnsupportedOperationException wenn eine andere Farbe enthalten ist.
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
                case 'H' -> colors[i] = Color.GRAY;
                default -> throw new UnsupportedOperationException("Unknown Color:" + c);
            }
        }
        return colors;
    }

    /**
     * Methode welche die Farben in 90-Grad Rotationen zurückgibt.
     * @param rotation  die Rotation im Uhrzeigersinn.
     * @return          Array von 4 Farben nach Rotation.
     */
    public Color[] getColors(Rotation rotation){
        if (rotation == null) {
            rotation = Rotation.DEGREE_0;
        }

        Color[] original = this.getColors();
        Color[] rotated = new Color[4];

        for (int i = 0; i < 4; i++) {
            rotated[i] = original[(i - rotation.getRotation() + 4) % 4];
        }
        return rotated;
    }

    /**
     * Methode, welche die Enum-Teile in einzelne Buchstaben trennt.
     *
     * @return  das Mosaikteil zertrennt in seinen Buchstaben.
     */
    public char[] splitTile() {
        return this.name().toCharArray();
    }

    /**
     * Methode, welche einen einzelnen Buchstaben anhand des Index zurückgibt.
     *
     * @return  den einzelnen Buchstaben anhand seines Index.
     */
    public char giveSingleTile(int index){
        return this.name().charAt(index);
    }
}
