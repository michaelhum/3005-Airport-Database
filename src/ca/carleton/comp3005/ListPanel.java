package ca.carleton.comp3005;


import javax.swing.*;
import java.awt.*;

// This is the Panel that contains represents the view of the
// Music Store

public class ListPanel extends JPanel {

    // These are the components
    private final JButton searchButton;
    private final JTextField searchText;
    private final JList destinationList;
    private final JList flightList;
    private final JList customerList;

    private final Font UIFont = new Font("Courier New", Font.BOLD, 16);

    public ListPanel() {
        super();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        this.setLayout(layout);

        this.setPreferredSize(new Dimension(1440, 900));

        this.destinationList = new JList();
        this.destinationList.setFont(this.UIFont);
        this.destinationList.setPrototypeCellValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        JScrollPane scrollPane = new JScrollPane(this.destinationList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridheight = 3;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
        layoutConstraints.weightx = 1.0;
        layoutConstraints.weighty = 1.0;
        layout.setConstraints(scrollPane, layoutConstraints);
        this.add(scrollPane);

        this.searchText = new JTextField("");
        this.searchText.setFont(this.UIFont);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridheight = 1;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        layoutConstraints.anchor = GridBagConstraints.EAST;
        layoutConstraints.weightx = 1.0;
        layoutConstraints.weighty = 0.0;
        layout.setConstraints(this.searchText, layoutConstraints);
        this.add(this.searchText);

        this.searchButton = new JButton("Search");
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridheight = 1;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        layoutConstraints.anchor = GridBagConstraints.EAST;
        layoutConstraints.weightx = 0.0;
        layoutConstraints.weighty = 0.0;
        layout.setConstraints(this.searchButton, layoutConstraints);
        this.add(this.searchButton);

        this.flightList = new JList();
        this.flightList.setFont(this.UIFont);
        this.flightList.setPrototypeCellValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        scrollPane = new JScrollPane(this.flightList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridheight = 2;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        layoutConstraints.anchor = GridBagConstraints.SOUTH;
        layoutConstraints.weightx = 1;
        layoutConstraints.weighty = 1;
        layout.setConstraints(scrollPane, layoutConstraints);
        this.add(scrollPane);

        this.customerList = new JList();
        this.customerList.setFont(this.UIFont);
        this.customerList.setPrototypeCellValue("xxxxxxxxxxxxxxx");
        scrollPane = new JScrollPane(this.customerList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 6;
        layoutConstraints.gridwidth = 3;
        layoutConstraints.gridheight = 5;
        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.anchor = GridBagConstraints.NORTHEAST;
        layoutConstraints.weightx = 0.5;
        layoutConstraints.weighty = 0.5;
        layout.setConstraints(scrollPane, layoutConstraints);
        this.add(scrollPane);
    }

    public JButton getSearchButton() {
        return this.searchButton;
    }

    public JList getDestinationList() {
        return this.destinationList;
    }

    public JList getFlightList() {
        return this.flightList;
    }

    public JTextField getSearchText() {
        return this.searchText;
    }

    public JList getCustomerList() {
        return this.customerList;
    }
}