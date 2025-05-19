package logic;

public class Position {

    /**
     * X-Koordinate
     */
    private final int column;
    /**
     * Y-Koordinate
     */
    private final int row;

    /**
     * Erstellt eine neue Position mit den Koordinaten column und row.
     *
     * @param column X-Koordinate
     * @param row Y-Koordinate
     */
    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    /**
     * Gibt die X-Koordinate der Position zurück.
     *
     * @return X-koordinate der Position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gibt die Y-Koordinate der Position zurück.
     *
     * @return Y-koordinate der Position
     */
    public int getRow() {
        return row;
    }
}
