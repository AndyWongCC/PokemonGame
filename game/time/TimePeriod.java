package game.time;

public enum TimePeriod {
    DAY("Day"),
    NIGHT("Night");


    /**
     * Label for the time period
     */
    private final String label;

    /**
     * Time period contructor
     * @param label for time period
     */
    TimePeriod(String label){
        this.label = label;
    }

    /**
     *
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
