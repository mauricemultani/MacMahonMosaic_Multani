package logic;

/**
 * Interface. Wird von den Klassen "Editor" und "Game" implementiert, da beide die Lösbarkeit verwenden.
 * Das Interface implementiert Methoden über die Lösbarkeit verbunden mit den Regeln.
 *
 * @author Maurice Singh Multani
 */
public interface Solvability {

    /**
     * Prüft, ob das Spiel lösbar ist.
     *
     * Spiel ist erfolgreich lösbar wenn:
     * 1. Zwei Teile, die sich an einer Kante berühren dieselbe Farbe haben.
     * 2. Alle möglich belegbaren Zellen belegt sind.
     *
     * Spiel ist nicht lösbar, wenn ein Teil falsch platziert ist.
     * Spieler wird auch über die Lösbarkeit im aktuellen Spielstand informiert.
     */
    default boolean solveGame(GameField gameField){
        return true;
    }

    /**
     * Prüft im aktuellen Spielstand, bei freien Stellen und verfügbaren Mosaikteilen, ob es eine mögliche Lösung gibt.
     */
    default void possibleSolvation(){

    }

    /**
     * Soll dem Spieler die Möglichkeit geben einen Tip für die Lösung zu bekommen.
     * Prüft zunächst auf eine mögliche Lösung.
     *
     * Wenn es eine mögliche Lösung gibt, wird eine leere Zelle belegt.
     *  - Die leere Zelle muss an Rand oder an min. einer belegten Zelle angrenzen.
     *
     *  Unendliche Nutzung möglich. Puzzle kann auch nur mit der Hilfe gelöst werden.
     */
    default void helpSolve() {

    }

    /**
     * Hilfsmethode um die Lösung zu prüfen.
     * Prüft, ob die Kanten dieselben Farben haben.
     */
    default void sameColouredEdges() {

    }

    /**
     * Hilfsmethode um die Lösung zu prüfen.
     * Prüft, ob alle Zellen belegt sind.
     */
    default void allTilesPlaced() {

    }

    /**
     * Prüfung zur Testung, ob das Spiel fertig ist.
     */
    default boolean gameDone(GameField gameField){
        return true;
    }
}
