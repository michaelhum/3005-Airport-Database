package ca.carleton.comp3005;

/**
 * Represents a customer.
 * <p/>
 * Created with IntelliJ IDEA.
 * Date: 08/12/14
 * Time: 9:09 AM
 */
public class Customer {

    private final int id;
    private final String givenName;
    private final String surName;
    private final Flight nextFlight;

    public Customer(final int id, final String givenName, final String surName, final Flight nextFlight) {

        this.id = id;
        this.givenName = givenName;
        this.surName = surName;
        this.nextFlight = nextFlight;
    }

    public int getId() {
        return this.id;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public String getSurName() {
        return this.surName;
    }

    public Flight getNextFlight() {
        return this.nextFlight;
    }

    @Override
    public String toString() {
        return this.givenName + " " + this.surName + " [Customer ID: " + this.id + "].";
    }
}
