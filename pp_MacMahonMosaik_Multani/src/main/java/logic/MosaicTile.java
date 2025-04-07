package logic;

import javafx.scene.image.Image;

/**
 * Repräsentiert ein Mosaik-Tile, welches auf dem Spielfeld platziert werden kann.
 * Visuelle Darstellung eines Mosaik-Tiles erfolgt über einem PNG-Bild.
 *
 * @author Maurice Singh Multani
 */
public class MosaicTile {

    /**
     * Das Bild, dass das Mosaik-Teil visuell darstellt.
     */
    private Image tileImage;

    /**
     * Konstruktor zur Initialisierung des Mosaik-Tiles mit einem Bild.
     * @param tileImage das Bild, das das Mosaik-Tile darstellt.
     */
    public MosaicTile(Image tileImage) {
        this.tileImage = tileImage;
    }

    /**
     * rotiert die Mosaikzelle um 90° im Uhrzeigersinn.
     */
    public void rotateTile(){

    }



}
