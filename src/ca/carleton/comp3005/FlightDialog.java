package ca.carleton.comp3005;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FlightDialog extends JDialog {

    private Flight theFlight;

    DialogClient theDialogClient;

    // These are the components of the dialog box
    private JLabel aLabel; //reuseable label variable

    private JTextField arrivalField; //name of the song
    private JTextField arrivalTimeField; //artist of the song
    private JTextField departureField; //price of the song
    private JTextField departureTimeField; //year of the song

    private JButton updateButton;
    private JButton deleteButton;
    private JButton cancelButton;

    Font UIFont = new Font("Courier New", Font.BOLD, 16);


    public FlightDialog(Frame owner, DialogClient aClient, String title, boolean modal, Flight flight) {
        super(owner, title, modal);

        this.theDialogClient = aClient;
        this.theFlight = flight;

        // Put all the components onto the window and given them initial values
        this.buildDialogWindow(this.theFlight);

        // Add listeners for the Ok and Cancel buttons as well as window closing
        this.updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                FlightDialog.this.updateButtonClicked();
            }
        });

        this.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                FlightDialog.this.deleteButtonClicked();
            }
        });


        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                FlightDialog.this.cancelButtonClicked();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                FlightDialog.this.cancelButtonClicked();
            }
        });

        this.setSize(400, 250);

    }

    // This code adds the necessary components to the interface
    private void buildDialogWindow(final Flight aFlight) {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints lc = new GridBagConstraints();
        this.getContentPane().setLayout(layout);


        lc.anchor = GridBagConstraints.EAST;
        lc.insets = new Insets(5, 5, 5, 5);

        this.aLabel = new JLabel("Arrival Destination");
        lc.gridx = 0;
        lc.gridy = 0;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.aLabel, lc);
        this.getContentPane().add(this.aLabel);

        this.aLabel = new JLabel("Arrival Time");
        lc.gridx = 0;
        lc.gridy = 1;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.aLabel, lc);
        this.getContentPane().add(this.aLabel);

        this.aLabel = new JLabel("Departure Destination");
        lc.gridx = 0;
        lc.gridy = 2;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.aLabel, lc);
        this.getContentPane().add(this.aLabel);

        this.aLabel = new JLabel("Departure Time");
        lc.gridx = 0;
        lc.gridy = 3;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.aLabel, lc);
        this.getContentPane().add(this.aLabel);


        this.aLabel = new JLabel("  "); //blank label
        lc.gridx = 0;
        lc.gridy = 5;
        lc.gridwidth = 3;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.aLabel, lc);
        this.getContentPane().add(this.aLabel);

        this.aLabel = new JLabel("  "); //blank label
        lc.gridx = 1;
        lc.gridy = 6;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 1.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.aLabel, lc);
        this.getContentPane().add(this.aLabel);

        // Add the name field
        this.arrivalField = new JTextField(aFlight.getArrivalDestination().toString());
        this.arrivalField.setFont(this.UIFont);
        lc.gridx = 1;
        lc.gridy = 0;
        lc.gridwidth = 3;
        lc.gridheight = 1;
        lc.fill = GridBagConstraints.BOTH;
        lc.weightx = 1.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.arrivalField, lc);
        this.getContentPane().add(this.arrivalField);

        // Add the address field
        this.arrivalTimeField = new JTextField(aFlight.getArrivalTime().toString());
        this.arrivalTimeField.setFont(this.UIFont);

        lc.gridx = 1;
        lc.gridy = 1;
        lc.gridwidth = 3;
        lc.gridheight = 1;
        lc.fill = GridBagConstraints.BOTH;
        lc.weightx = 1.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.arrivalTimeField, lc);
        this.getContentPane().add(this.arrivalTimeField);


        // Add the year field
        this.departureField = new JTextField(aFlight.getDepartureDestination().toString());
        this.departureField.setFont(this.UIFont);
        lc.gridx = 1;
        lc.gridy = 2;
        lc.gridwidth = 3;
        lc.gridheight = 1;
        lc.fill = GridBagConstraints.BOTH;
        lc.weightx = 1.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.departureField, lc);
        this.getContentPane().add(this.departureField);


        // Add the price field
        this.departureTimeField = new JTextField(aFlight.getDepartureTime().toString());
        this.departureTimeField.setFont(this.UIFont);
        lc.gridx = 1;
        lc.gridy = 3;
        lc.gridwidth = 3;
        lc.gridheight = 1;
        lc.fill = GridBagConstraints.BOTH;
        lc.weightx = 1.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.departureTimeField, lc);
        this.getContentPane().add(this.departureTimeField);


        // Add the Update button
        this.updateButton = new JButton("UPDATE");

        lc.gridx = 1;
        lc.gridy = 6;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.updateButton, lc);
        this.getContentPane().add(this.updateButton);

        // Add the Delete button
        this.deleteButton = new JButton("DELETE");

        lc.gridx = 2;
        lc.gridy = 6;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.deleteButton, lc);
        this.getContentPane().add(this.deleteButton);

        // Add the Cancel button
        this.cancelButton = new JButton("CANCEL");

        lc.gridx = 3;
        lc.gridy = 6;
        lc.gridwidth = 1;
        lc.gridheight = 1;
        lc.weightx = 0.0;
        lc.weighty = 0.0;
        layout.setConstraints(this.cancelButton, lc);
        this.getContentPane().add(this.cancelButton);
    }


    private void updateButtonClicked() {
        if (this.theDialogClient != null) this.theDialogClient.dialogFinished(DialogClient.operation.UPDATE);
        this.dispose();
    }

    private void deleteButtonClicked() {
        if (this.theDialogClient != null) this.theDialogClient.dialogFinished(DialogClient.operation.DELETE);
        this.dispose();
    }

    private void cancelButtonClicked() {
        if (this.theDialogClient != null) this.theDialogClient.dialogCancelled();
        this.dispose();
    }
}