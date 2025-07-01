package logic;

/**
 * Klasse für die Position.
 * Wird verwendet, um Positionen zurückzugeben oder neue zu erstellen.
 *
 * @param column X-Koordinate
 * @param row    Y-Koordinate
 * @author Maurice Singh Multani
 */
public record Position(int column, int row) {

    /**
     * Erstellt eine neue Position mit den Koordinaten column und row.
     *
     * @param column X-Koordinate
     * @param row    Y-Koordinate
     */
    public Position {
    }

    /**
     * Gibt die X-Koordinate der Position zurück.
     *
     * @return X-koordinate der Position
     */
    @Override
    public int column() {
        return column;
    }

    /**
     * Gibt die Y-Koordinate der Position zurück.
     *
     * @return Y-koordinate der Position
     */
    @Override
    public int row() {
        return row;
    }
}
