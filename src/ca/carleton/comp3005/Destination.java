package ca.carleton.comp3005;

/**
 * Represents a destination     .
 *
 * Created with IntelliJ IDEA.
 * Date: 08/12/14
 * Time: 12:58 AM
 */
public class Destination {

    private final int id;
    private final String name;
    private final String country;
    private final boolean isSeasonal;
    private final Airport airport;

    public Destination(final int id, final String name, final String country, final boolean isSeasonal,
                       final Airport airport) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.isSeasonal = isSeasonal;
        this.airport = airport;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public boolean isSeasonal() {
        return this.isSeasonal;
    }

    @Override
    public String toString() {
        return "[" + this.airport + "] " + this.name + ", " + this.country;
    }
}
