package logic;

import javafx.scene.layout.GridPane;

/**
 * Repräsentiert das Spiel.
 * Verwaltet das Spielfeld, die Platzierung der Mosaikteile und den aktuellen Spielstand.
 * Implementiert das Interface "Solvability", welches Methoden zur Überprüfung der Lösbarkeit des Puzzles bereitstellt.
 * Die Klasse ist verantwortlich für die Spiellogik, Initialisieren und Verwalten des Spiels.
 *
 * @author Maurice Singh Multani
 */
public class Game implements Solvability {

    /**
     * Das GridPane auf dass, das Spielfeld dargestellt wird.
     */
    private GridPane gameField;

    /**
     * Ein Array zur Speicherung des aktuellen Spielstands.
     */
    private String[][] ongoingGame;

    /**
     * Konstruktor für ein neues Spiel.
     */
    public Game(){
        initializeGame();

    }

    /**
     * Konstruktor für Testzwecke. Erzeugt ein befindliches Spiel mitten im Spielgeschehen.
     * Nimmt das Spielfeld in Form eines StringArrays entgegen.
     * @param ongoingGame das laufende Spiel
     */
    public Game(String[][] ongoingGame){
        this.ongoingGame = ongoingGame;
    }

    /**
     * Initialisiert das Spiel.
     * Löscht alle Zellen und setzt das Spielfeld zurück.
     */
    public void initializeGame() {
        gameField.getChildren().clear();

    }

    /**
     * Entfernt alle bereits belegten Zellen und setzt das Spielfeld zurück.
     */
    public void clearGameField() {
        gameField.getChildren().clear();
    }

    /**
     * Klont das Spielfeld. Wird für den Wechsel in den Editor-Modus benötigt.
     */
    public void cloneGameField() {

    }

    /**
     * Speichert das laufende Spiel.
     *
     * Zu beachten wäre, dass es speichert, wenn das Spiel schon einen Namen hat.
     */
    public void saveGame(){

    }

    /**
     * Lädt ein gespeichertes Spiel
     */
    public void loadGame(){

    }

    /**
     * Benachrichtigt dem Spieler, dass er das Spiel gewonnen hat.
     * Der Spieler wird erst dann benachrichtigt, wenn alle Zellen belegt und die Farb-Regel nicht verletzt wird.
     */
    public void winningGame(){

    }

    /**
     * Dem Spieler wird sichtbar angezeigt, welche Zellen nicht die passenden Farben an den Kanten haben.
     * Soll nur eine Anzeige sein, der Spieler muss den Fehler nicht sofort beheben.
     */
    public void showNotMatchingEdge(){

    }
}
