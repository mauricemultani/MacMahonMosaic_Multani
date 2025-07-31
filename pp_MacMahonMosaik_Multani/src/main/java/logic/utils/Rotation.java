package logic.utils;

/**
 * Eine Enum-Klasse für die Rotation.
 *
 * @author Maurice Singh Multani
 */
public enum Rotation {

    DEGREE_0,
    DEGREE_90,
    DEGREE_180,
    DEGREE_270;

    /**
     * rotiert die Mosaikzelle um 90° im Uhrzeigersinn.
     */
    public Rotation next(){
        return switch (this) {
            case DEGREE_0 -> DEGREE_90;
            case DEGREE_90 -> DEGREE_180;
            case DEGREE_180 -> DEGREE_270;
            case DEGREE_270 -> DEGREE_0;
        };
    }

    /**
     * Gibt die Rotation in Zahlen von 0 bis 3 wieder.
     * @return  Die Rotation in einer Zahl umgewandelt.
     */
    public int getRotation() {
        return switch (this) {
            case DEGREE_0 -> 0;
            case DEGREE_90 -> 1;
            case DEGREE_180 -> 2;
            case DEGREE_270 -> 3;
        };
    }

    public Rotation getCurrentRotation() {
        return switch (this) {
            case DEGREE_0 -> DEGREE_0;
            case DEGREE_90 -> DEGREE_90;
            case DEGREE_180 -> DEGREE_180;
            case DEGREE_270 -> DEGREE_270;
        };
    }
}
