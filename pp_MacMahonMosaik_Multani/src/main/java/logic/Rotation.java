package logic;

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

    public int getRotation() {
        return switch (this) {
            case DEGREE_0 -> 0;
            case DEGREE_90 -> 1;
            case DEGREE_180 -> 2;
            case DEGREE_270 -> 3;
        };
    }
}
