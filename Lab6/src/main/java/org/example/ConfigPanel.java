package org.example;

import javax.swing.*;
public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesCombo;
    JButton createButton;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));

        linesLabel = new JLabel("Probability of lines:");
        linesCombo = new JComboBox(new DefaultComboBoxModel());

        JButton newGame = new JButton("NewGame");

        //create the rest of the components
        add(dotsLabel); //JPanel uses FlowLayout by default
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(newGame);


    }
}

