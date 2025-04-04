package gui.management;

public abstract class Management {

    /**
     * Starten des Spiels.
     */
    public abstract void initializeGame();

    /**
     * Speicherung des Spielstands.
     */
    public abstract void saveGame();

    /**
     * Laden eines Spielstands.
     */
    public abstract void loadGame();

    /**
     * Lösen eines Spiels.
     */
    public abstract void solveGame();

    /**
     * Neustarten des Spiels
     */
    public abstract void restartGame();

    /**
     * Setzt das Spielfeld zurück.
     */
    public abstract void resetGame();
}
