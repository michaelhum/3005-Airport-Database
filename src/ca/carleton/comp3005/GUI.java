package ca.carleton.comp3005;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame implements DialogClient {

    Connection connection;
    Statement statement;

    ArrayList<Destination> destinations = new ArrayList<Destination>();
    ArrayList<Flight> flights = new ArrayList<Flight>();
    ArrayList<Customer> customers = new ArrayList<Customer>();

    private Destination selectedDestination;

    private Flight selectedFlight;

    private Flight flightBeingEdited;

    // Store the view that contains the components
    ListPanel view; // panel of GUI components for the main window
    GUI thisFrame;

    // Here are the component listeners
    ActionListener theSearchButtonListener;
    ListSelectionListener flightListSelectionListener;
    ListSelectionListener destinationListSelectionListener;
    MouseListener doubleClickFlightListListener;
    KeyListener keyListener;

    public GUI(final String title, final Connection connection,
               final Statement statement, final ArrayList<Destination> destinations,
               final ArrayList<Flight> flights, final ArrayList<Customer> customers) {
        super(title);
        this.connection = connection;
        this.statement = statement;

        this.destinations = destinations;
        this.flights = flights;
        this.customers = customers;
        this.selectedDestination = null;
        this.selectedFlight = null;
        this.thisFrame = this;

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                try {
                    System.out.println("Closing Database Connection");
                    GUI.this.connection.close();
                } catch (final SQLException exception) {
                    exception.printStackTrace();
                }
                System.exit(0);
            }
        });

        this.add(this.view = new ListPanel());

        this.theSearchButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                GUI.this.search();
            }
        };

        this.destinationListSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent event) {
                GUI.this.selectDestination();
            }
        };

        this.flightListSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(final ListSelectionEvent event) {
                GUI.this.selectFlight();
            }
        };

        this.doubleClickFlightListListener = new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent event) {
                if (event.getClickCount() == 2) {
                    final JList theList = (JList) event.getSource();
                    final int index = theList.locationToIndex(event.getPoint());
                    GUI.this.flightBeingEdited = (Flight) theList
                            .getModel().getElementAt(index);
                    System.out.println("Double Click on: "
                            + GUI.this.flightBeingEdited);

                    final FlightDialog dialog = new FlightDialog(
                            GUI.this.thisFrame,
                            GUI.this.thisFrame,
                            "Flight Details",
                            true,
                            GUI.this.flightBeingEdited);

                    dialog.setVisible(true);

                }

            }
        };

        this.keyListener = new KeyListener() {

            @Override
            public void keyPressed(final KeyEvent arg0) {
            }

            @Override
            public void keyReleased(final KeyEvent arg0) {
            }

            @Override
            public void keyTyped(final KeyEvent arg0) {

                final int keyChar = arg0.getKeyChar();

                if (keyChar == KeyEvent.VK_ENTER) {
                    GUI.this.search();
                }

            }
        };

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 300);

        // Start off with everything updated properly to reflect the model state
        this.update();
    }

    @Override
    public void dialogCancelled() {
        System.out.println("Dialog cancelled...");
        this.flightBeingEdited = null;
        this.update();
    }

    @Override
    public void dialogFinished(final DialogClient.operation requestedOperation) {
        System.out.println("Dialog finished...");
        if (requestedOperation == DialogClient.operation.UPDATE) {

        }

        this.update();
    }

    // Disable all listeners
    private void disableListeners() {
        this.view.getSearchButton().removeActionListener(
                this.theSearchButtonListener);
        this.view.getDestinationList().removeListSelectionListener(
                this.destinationListSelectionListener);
        this.view.getFlightList().removeListSelectionListener(
                this.flightListSelectionListener);
        this.view.getFlightList().removeMouseListener(
                this.doubleClickFlightListListener);
        this.view.getSearchText().removeKeyListener(this.keyListener);
    }

    // Enable all listeners
    private void enableListeners() {
        this.view.getSearchButton().addActionListener(
                this.theSearchButtonListener);
        this.view.getDestinationList().addListSelectionListener(
                this.destinationListSelectionListener);
        this.view.getFlightList().addListSelectionListener(
                this.flightListSelectionListener);
        this.view.getFlightList().addMouseListener(
                this.doubleClickFlightListListener);
        this.view.getSearchText().addKeyListener(this.keyListener);
    }

    // This is called when the user clicks the search button
    private void search() {
        System.out.println("Search clicked...");

        String searchQuery = this.view.getSearchText().getText().trim();

        searchQuery = searchQuery.toUpperCase();

        String sql = "select flights.id as flight_id, depart_destination_id, arrive_destination_id, departure_time, arrival_time from flights join destinations on destinations.id = flights.depart_destination_id where name LIKE ? OR country LIKE ?";

        boolean empty = false;

        if (searchQuery.equals("*") || searchQuery.equals("%") || searchQuery.equals("")) {
            empty = true;
        }

        if (empty) {
            sql = "select flights.id as flight_id, depart_destination_id, arrive_destination_id, departure_time, arrival_time from flights;";
        }

        if (this.selectedDestination != null) {
            sql = "select flights.id as flight_id, destinations.id as destination_id, depart_destination_id, arrive_destination_id, departure_time, arrival_time from flights join destinations on destinations.id = flights.depart_destination_id where name LIKE ? OR country LIKE ? and destinations.id = " + this.selectedDestination.getId();

            if (empty) {
                sql = "select flights.id as flight_id, depart_destination_id, arrive_destination_id, departure_time, arrival_time from flights where depart_destination_id = " + this.selectedDestination.getId() + " or arrive_destination_id = " + this.selectedDestination.getId() + ";";
            }
        }

        System.out.println("Query -- " + sql);

        try {
            final PreparedStatement statement = this.connection.prepareStatement(sql);

            if (empty) {
                // do nothing
            } else {
                statement.setString(1, "%" + searchQuery + "%");
                statement.setString(2, "%" + searchQuery + "%");
            }

            final ResultSet resultSet = statement.executeQuery();

            final ArrayList<Flight> results = new ArrayList<Flight>();
            while (resultSet.next()) {
                final Flight flight = new Flight(
                        resultSet.getInt("flight_id"),
                        this.destinations.get(resultSet.getInt("depart_destination_id") - 1),
                        this.destinations.get(resultSet.getInt("arrive_destination_id") - 1),
                        new java.util.Date(resultSet.getTimestamp("departure_time").getTime()),
                        new java.util.Date(resultSet.getTimestamp("arrival_time").getTime()));
                results.add(flight);
            }

            resultSet.close();

            this.flights = results;

        } catch (final Exception exception) {
            exception.printStackTrace();
        }

        this.view.getDestinationList().clearSelection();
        this.view.getSearchText().setText("");
        this.update();
    }

    private void selectDestination() {
        System.out.println("Destination clicked...");

        this.selectedDestination = (Destination) this.view.getDestinationList().getSelectedValue();
        this.update();
    }

    private void selectFlight() {
        System.out.println("Flight clicked...");

        this.selectedFlight = (Flight) this.view.getFlightList().getSelectedValue();
        this.update();
    }

    // Update the components
    private void update() {
        this.disableListeners();
        this.updateList();
        this.updateSearchButton();
        this.enableListeners();
    }

    // Update the list
    private void updateList() {
        this.view.getDestinationList().setListData(this.destinations.toArray(new Destination[this.destinations.size()]));
        this.view.getFlightList().setListData(this.flights.toArray(new Flight[this.customers.size()]));

        if (this.selectedDestination != null) {
            this.view.getDestinationList().setSelectedValue(this.selectedDestination, true);
        }
        if (this.selectedFlight != null) {
            this.view.getFlightList().setSelectedValue(this.selectedFlight, true);

            final List<Customer> selectedCustomers = new ArrayList<Customer>();

            for (final Customer customer : this.customers) {
                if (customer.getNextFlight() != null) {
                    if (customer.getNextFlight().getId() == this.selectedFlight.getId()) {
                        selectedCustomers.add(customer);
                    }
                }
            }

            this.view.getCustomerList().setListData(selectedCustomers.toArray(new Customer[selectedCustomers.size()]));

        }
    }

    // Update the remove button
    private void updateSearchButton() {
        this.view.getSearchButton().setEnabled(true);
    }

}