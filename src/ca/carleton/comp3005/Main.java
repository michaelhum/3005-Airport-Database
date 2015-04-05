package ca.carleton.comp3005;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {

    private static final String LOAD_AIRPORTS_QUERY = "select * from airports";

    private static final String LOAD_DESTINATIONS_QUERY = "select * from destinations";

    private static final String LOAD_FLIGHTS_QUERY = "select * from flights";

    private static final String LOAD_CUSTOMERS_QUERY = "select * from customers";

    public static void main(final String[] args) {


        System.out.println("Blah");
        GUI frame = null;

        try {

            Class.forName("org.sqlite.JDBC");

            final Connection connection = DriverManager.getConnection("jdbc:sqlite:assignment5");
            final Statement statement = connection.createStatement();

            // Initial load of all the useful stuff...
            final ArrayList<Airport> airports = new ArrayList<Airport>();
            final ArrayList<Destination> destinations = new ArrayList<Destination>();
            final ArrayList<Flight> flights = new ArrayList<Flight>();
            final ArrayList<Customer> customers = new ArrayList<Customer>();

            ResultSet resultSet = statement.executeQuery(LOAD_AIRPORTS_QUERY);

            while (resultSet.next()) {
                final Airport airport = new Airport(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code"),
                        resultSet.getString("hours_of_operation"));
                airports.add(airport);
            }

            resultSet.close();

            resultSet = statement.executeQuery(LOAD_DESTINATIONS_QUERY);

            while (resultSet.next()) {
                final Destination destination = new Destination(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country"),
                        resultSet.getBoolean("is_seasonal"),
                        getAirport(airports, resultSet.getInt("airport_id")));
                destinations.add(destination);
            }
            resultSet.close();

            resultSet = statement.executeQuery(LOAD_FLIGHTS_QUERY);

            while (resultSet.next()) {
                final Flight flight = new Flight(
                        resultSet.getInt("id"),
                        destinations.get(resultSet.getInt("depart_destination_id") - 1),
                        destinations.get(resultSet.getInt("arrive_destination_id") - 1),
                        new Date(resultSet.getTimestamp("departure_time").getTime()),
                        new Date(resultSet.getTimestamp("arrival_time").getTime()));
                flights.add(flight);
            }

            resultSet.close();

            resultSet = statement.executeQuery(LOAD_CUSTOMERS_QUERY);

            while (resultSet.next()) {
                final Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("given_name"),
                        resultSet.getString("surname"),
                        getFlight(flights, resultSet.getInt("next_flight_id")));
                customers.add(customer);
            }

            resultSet.close();

            for (Flight flight : flights) {
                System.out.println(flight);
            }

            frame = new GUI("Flight Management - Michael Hum 100883995", connection, statement, destinations, flights, customers);

        } catch (final Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);

    }

    private static Airport getAirport(final List<Airport> airports, final int idToSearch) {
        for (final Airport airport : airports) {
            if (airport.getId() == idToSearch) {
                return airport;
            }
        }
        return null;
    }

    private static Flight getFlight(final List<Flight> flights, final int idToSearch) {
        for (final Flight flight : flights) {
            if (flight.getId() == idToSearch) {
                return flight;
            }
        }
        return null;
    }

}
