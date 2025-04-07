package gui.gridControllers;

import javafx.scene.layout.GridPane;

/**
 * Abstrakte Klasse. Ist für die Steuerung der GridPanes die an der <BorderPane> left, right und bottom sind.
 * Methoden werden von den Klassen "GridBottomController", "GridLeftController" und "GridRightController" beerbt.
 *
 * Die Steuerung des Spielfelds (GameFieldController) ist nicht teil der abstrakten Klasse, da sie noch weitere Methoden implementiert.
 *
 * @author Maurice Singh Multani
 */
public abstract class Controller {

    /**
     * Das entsprechende GridPane, das für die Darstellung verwendet wird.
     */
    protected final GridPane gridPane;


    /**
     *  Konstruktor zur Zuweisung des GridPanes.
     * @param gridPane Das GridPane, was vom Controller verwaltet wird.
     */
    public Controller(GridPane gridPane) {
        this.gridPane = gridPane;
    }


    /**
     * String, dass Bilder speichert.
     * Wird in den Unterklassen implementiert
     *
     * @return Ein Array von Strings der gespeicherten Bilder.
     */
    public abstract String[] images();


    /**
     *  Initialisierung der Bilder auf das GridPane.
     *  Wird in den Unterklassen implementiert.
     */
    public abstract void initImages();
}
