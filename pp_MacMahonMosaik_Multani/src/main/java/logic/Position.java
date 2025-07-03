package logic;

/**
 * Klasse für die Position.
 * Wird verwendet, um Positionen zurückzugeben oder neue zu erstellen.
 *
 * @param row X-Koordinate
 * @param column    Y-Koordinate
 * @author Maurice Singh Multani
 */
public record Position(int row, int column) {

    /**
     * Erstellt eine neue Position mit den Koordinaten row und column.
     *
     * @param row X-Koordinate
     * @param column    Y-Koordinate
     */
    public Position {
    }

    /**
     * Gibt die X-Koordinate der Position zurück.
     *
     * @return X-koordinate der Position
     */
    public int row() {
        return row;
    }

    /**
     * Gibt die Y-Koordinate der Position zurück.
     *
     * @return Y-koordinate der Position
     */
    public int column() {
        return column;
    }
}
