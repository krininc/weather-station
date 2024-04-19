package edu.rit.croatia.swen383.g4.ws.ui;

import edu.rit.croatia.swen383.g4.ws.WeatherStation;
import edu.rit.croatia.swen383.g4.ws.observer.Observer;
import edu.rit.croatia.swen383.g4.ws.util.MeasurementUnit;
import edu.rit.croatia.swen383.g4.ws.util.DataExporter;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.EnumMap;
import java.io.IOException;

/**
 * SwingUI class that provides a graphical user interface for displaying sensor readings
 * from a WeatherStation object and exporting these readings to CSV or JSON formats.
 */
public class SwingUI extends JFrame implements Observer {
    private EnumMap<MeasurementUnit, JLabel> temperatureLabels; // Holds JLabels for each MeasurementUnit
    private WeatherStation ws; // Associated WeatherStation object
    private static final Font labelFont = new Font(Font.SERIF, Font.PLAIN, 46); // Font for JLabels

    /**
     * Constructs a SwingUI object associated with a WeatherStation.
     * Initializes components and sets up the window.
     *
     * @param ws the WeatherStation to which this UI will be attached
     */
    public SwingUI(WeatherStation ws) {
        super("Weather Station");
        this.ws = ws;
        ws.attach(this);
        initializeUIComponents();
        setupWindow();
        addExportButtons();
    }

    /**
     * Sets up the JFrame window properties.
     */
    public void setupWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Initializes the UI components.
     */
    public void initializeUIComponents() {
        setLayout(new GridLayout(1, 0));  // One row, variable number of columns
        temperatureLabels = new EnumMap<>(MeasurementUnit.class);
        
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            JPanel panel = createPanel(unit);
            JLabel label = createLabel(unit.name(), panel);
            temperatureLabels.put(unit, label);
            add(panel);
        }
    }

    /**
     * Creates a JLabel with specified text and adds it to a panel.
     *
     * @param title the text for the JLabel
     * @param panel the JPanel to which the label will be added
     * @return the created JLabel
     */
    private JLabel createLabel(String title, JPanel panel) {
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(labelFont);
        panel.add(label);
        return label;
    }

    /**
     * Creates a JPanel that will contain labels.
     *
     * @param unit the MeasurementUnit that defines the type of data shown on the panel
     * @return the created JPanel
     */
    private JPanel createPanel(MeasurementUnit unit) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        createLabel(unit.name(), panel);  // Title for panel based on the unit
        return panel;
    }

    /**
     * Adds buttons to export data to CSV and JSON formats.
     */
    private void addExportButtons() {
        JButton exportCSVButton = new JButton("Export to CSV");
        JButton exportJSONButton = new JButton("Export to JSON");

        exportCSVButton.addActionListener(e -> exportData("CSV"));
        exportJSONButton.addActionListener(e -> exportData("JSON"));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(exportCSVButton);
        buttonPanel.add(exportJSONButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);  // Add buttons at the bottom of the frame
    }

    /**
     * Exports the logged sensor data to a specified format.
     *
     * @param format the format to export the data ("CSV" or "JSON")
     */
    private void exportData(String format) {
        try {
            String logFilePath = "logfile.txt";  // Path to the log file
            String exportPath = "logfile." + format.toLowerCase();  // Path for the exported file

            if ("CSV".equals(format)) {
                DataExporter.exportAsCSV(logFilePath, exportPath);
            } else if ("JSON".equals(format)) {
                DataExporter.exportAsJSON(logFilePath, exportPath);
            }
            JOptionPane.showMessageDialog(this, "Data exported to " + format + " successfully!",
                                          "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to export data: " + ex.getMessage(),
                                          "Export Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates the UI with the latest sensor readings.
     * This method is called whenever the observed WeatherStation object notifies its observers.
     */
    @Override
    public void update() {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            setJLabel(unit, ws.getReading(unit));
        }
    }

    /**
     * Sets the text of a JLabel corresponding to a specific MeasurementUnit.
     *
     * @param unit the MeasurementUnit whose JLabel's text needs to be set
     * @param temperature the value to set as the JLabel's text
     */
    public void setJLabel(MeasurementUnit unit, double temperature) {
        JLabel label = temperatureLabels.get(unit);
        if (label != null) {
            SwingUtilities.invokeLater(() -> label.setText(String.format("%6.2f", temperature)));
        }
    }
}
