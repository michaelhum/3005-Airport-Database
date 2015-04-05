package ca.carleton.comp3005;

/**
 * Represnts an airport.
 *
 * Created with IntelliJ IDEA.
 * Date: 08/12/14
 * Time: 1:22 AM
 */
public class Airport {

    private final int id;
    private final String name;
    private final String code;
    private final String hoursOfOperation;

    public Airport(final int id, final String name, final String code, final String hoursOfOperation) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.hoursOfOperation = hoursOfOperation;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getHoursOfOperation() {
        return this.hoursOfOperation;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
