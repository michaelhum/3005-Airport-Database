package ca.carleton.comp3005;

import java.util.Date;

/**
 * Represents a flight.
 *
 * Created with IntelliJ IDEA.
 * Date: 08/12/14
 * Time: 1:13 AM
 */
public class Flight {

    private final int id;

    private final Destination departureDestination;

    private final Destination arrivalDestination;

    private final Date departureTime;

    private final Date arrivalTime;

    public Flight(final int id, final Destination departureDestination, final Destination arrivalDestination, final Date departureTime, final Date arrivalTime) {
        this.id = id;
        this.departureDestination = departureDestination;
        this.arrivalDestination = arrivalDestination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() {
        return this.arrivalTime;
    }

    public int getId() {
        return this.id;
    }

    public Destination getDepartureDestination() {
        return this.departureDestination;
    }

    public Destination getArrivalDestination() {
        return this.arrivalDestination;
    }

    public Date getDepartureTime() {
        return this.departureTime;
    }

    @Override
    public String toString() {
        return "Flight " + this.id + ": " + this.departureDestination + " to " + this.arrivalDestination + ".";
    }
}
